package ru.clonsaldafon.shoppinglistapp.presentation.view.products.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory
import ru.clonsaldafon.shoppinglistapp.data.model.group.AddProductRequest
import ru.clonsaldafon.shoppinglistapp.domain.product.AddProductUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.GetCategoriesUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.GetProductsByCategoryUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val getTokenUseCase: GetTokenUseCase
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
            is AddProductEvent.OnSubmit -> addProduct(
                productId = event.productId,
                quantity = event.quantity,
                onComplete = event.onComplete
            )
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
        viewModelScope.launch {
            try {
                when (val response = getCategoriesUseCase().toUiState()) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                categories = response.value
                            )
                        }
                    }
                    is UiState.Failure -> {
                        when (response.code) {
                            401 -> {
                                when (val refreshResponse = getTokenUseCase().toUiState()) {
                                    is UiState.Success -> loadCategories()
                                    is UiState.Failure -> {}
                                    is UiState.Loading -> {}
                                }
                            }
                        }
                    }
                    is UiState.Loading -> {}
                }
            } catch (e: Exception) {
                Log.e("error", e.message!!)
            } finally {
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun updateCategory(value: Category) {
        _uiState.update {
            it.copy(
                category = value.name!!,
                categoryId = value.categoryId!!,
                isLoading = true
            )
        }

        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                val response = getProductsByCategoryUseCase(_uiState.value.categoryId).toUiState()

                when (response) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                products = response.value
                            )
                        }
                    }
                    is UiState.Failure -> {
                        when (response.code) {
                            401 -> {
                                when (val refreshResponse = getTokenUseCase().toUiState()) {
                                    is UiState.Success -> loadProducts()
                                    is UiState.Failure -> {}
                                    is UiState.Loading -> {}
                                }
                            }
                        }
                    }
                    is UiState.Loading -> {}
                }
            } catch (e: Exception) {
                Log.e("error", e.message!!)
            } finally {
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
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

    private fun updateQuantity(value: String) {
        val intValue = if (value.isEmpty()) 0 else value.toInt()
        _uiState.update {
            it.copy(
                quantity = intValue,
                isQuantityInvalid = intValue < 1 || intValue > 1000
            )
        }
    }

    private fun addProduct(
        productId: Int,
        quantity: Int,
        onComplete: (isSuccess: Boolean?,
                     quantityErrorMessage: String?) -> Unit
    ) {
        _uiState.update {
            it.copy(
                isSubmitting = true
            )
        }

        viewModelScope.launch {
            try {
                val response = addProductUseCase(
                    groupId = _uiState.value.groupId,
                    request = AddProductRequest(
                        productNameId = _uiState.value.productId,
                        quantity = _uiState.value.quantity
                    )
                ).toUiState()

                when (response) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                isSuccess = true
                            )
                        }
                    }
                    is UiState.Failure -> {
                        when (response.code) {
                            401 -> {
                                when (val refreshResponse = getTokenUseCase().toUiState()) {
                                    is UiState.Success ->
                                        addProduct(productId, quantity, onComplete)
                                    is UiState.Failure -> {}
                                    is UiState.Loading -> {}
                                }
                            }
                        }
                    }
                    is UiState.Loading -> {}
                }
            } catch (e: Exception) {
                Log.e("error", e.message!!)
            } finally {
                onComplete(
                    _uiState.value.isSuccess,
                    _uiState.value.quantityErrorMessage
                )

                _uiState.update {
                    it.copy(
                        isSubmitting = false
                    )
                }
            }
        }
    }

}