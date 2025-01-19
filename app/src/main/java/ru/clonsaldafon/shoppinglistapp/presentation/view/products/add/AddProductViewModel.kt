package ru.clonsaldafon.shoppinglistapp.presentation.view.products.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.data.model.Product
import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory
import ru.clonsaldafon.shoppinglistapp.data.model.group.AddProductRequest
import ru.clonsaldafon.shoppinglistapp.domain.product.AddProductUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.GetCategoriesUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.GetProductsByCategoryUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.ResponseHandler
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddProductUiState())
    val uiState: StateFlow<AddProductUiState>
        get() = _uiState

    init {
        loadCategories()
    }

    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.OnCategoryChanged -> updateCategory(event.value)
            is AddProductEvent.OnProductChanged -> updateProduct(event.value)
            is AddProductEvent.OnQuantityChanged -> updateQuantity(event.value)
            is AddProductEvent.OnSubmit -> addProduct(event.onComplete)
        }
    }

    fun setGroupId(groupId: String) {
        _uiState.update {
            it.copy(
                groupId = groupId
            )
        }
    }

    fun setQuantityErrorMessage(message: String) {
        _uiState.update {
            it.copy(
                quantityErrorMessage = message,
                isQuantityInvalid = true
            )
        }
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val value = ResponseHandler.handle(
                request = { getCategoriesUseCase() },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            updateCategories(value)
        }

        _uiState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    private fun updateCategory(value: Category) {
        _uiState.update {
            it.resetProduct()
                .copy(
                    category = value.name!!,
                    categoryId = value.categoryId!!
                )
        }

        loadProducts()
    }

    private fun updateCategories(value: List<Category>?) {
        _uiState.update {
            it.copy(
                categories = value
            )
        }
    }

    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val value = ResponseHandler.handle(
                request = {
                    getProductsByCategoryUseCase(
                        categoryId = _uiState.value.categoryId
                    )
                },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            updateProducts(value)
        }
    }

    private fun updateProduct(value: ProductByCategory) {
        _uiState.update {
            it.copy(
                product = value.name!!,
                productId = value.productNameId!!
            )
        }
    }

    private fun updateProducts(value: List<ProductByCategory>?) {
        _uiState.update {
            it.copy(
                products = value
            )
        }
    }

    private fun updateQuantity(value: String) {
        val intValue = if (value.isEmpty()) 0 else value.toInt()
        _uiState.update {
            it.copy(
                quantity = value,
                isQuantityInvalid = intValue < 1 || intValue > 1000
            )
        }
    }

    private fun addProduct(
        onComplete: (isSuccess: Boolean?,
                     quantityErrorMessage: String?) -> Unit
    ) {
        _uiState.update {
            it.copy(
                isSubmitting = true
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            ResponseHandler.handle(
                request = {
                    addProductUseCase(
                        groupId = _uiState.value.groupId,
                        request = AddProductRequest(
                            productNameId = _uiState.value.productId,
                            quantity = _uiState.value.quantity.toInt()
                        )
                    )
                },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onNotFound = { onError("Группа не найдена") },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )
        }

        _uiState.update {
            it.copy(
                isSuccess = true,
                isSubmitting = false
            )
        }

        onComplete(
            _uiState.value.isSuccess,
            _uiState.value.quantityErrorMessage
        )
    }

    private fun onBadRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            ResponseHandler.handle(
                request = { logInUseCase() },
                onBadRequest = { onBadRequest() },
                onNotFound = {  },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )
        }
    }

    private fun onError(message: String) {
        _uiState.update {
            it.copy(
                error = message
            )
        }
    }

}