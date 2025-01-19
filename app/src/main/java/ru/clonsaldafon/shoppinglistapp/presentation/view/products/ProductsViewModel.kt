package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.Product
import ru.clonsaldafon.shoppinglistapp.data.model.group.UpdateProductRequest
import ru.clonsaldafon.shoppinglistapp.domain.group.GetProductsUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.DeleteProductUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.UpdateProductUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.ResponseHandler
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState>
        get() = _uiState

    fun onEvent(event: ProductsEvent) {
        when (event) {
            is ProductsEvent.OnProductDeleted -> deleteProduct(event.groupId, event.productId)
            is ProductsEvent.OnProductUpdated -> updateProduct(event.groupId, event.productId)
            is ProductsEvent.OnProductsLoaded -> {}
            is ProductsEvent.OnBuyWindowVisibilityChanged -> updateBuyWindowVisibility(event.value)
            is ProductsEvent.OnCurrentPriceUpdated -> updateCurrentPrice(event.value)
            is ProductsEvent.OnCurrentProductIdUpdated -> updateCurrentProductId(event.value)
            is ProductsEvent.OnCurrentProductBought -> updateCurrentProductBought(event.value)
            is ProductsEvent.OnCurrentQuantityUpdated -> updateCurrentQuantity(event.value)
        }
    }

    fun setGroupId(groupId: String) {
        _uiState.update {
            it.copy(
                groupId = groupId
            )
        }
    }

    fun loadProducts() {
        viewModelScope.launch {
            val value = ResponseHandler.handle(
                request = {
                    getProductsUseCase(
                        groupId = _uiState.value.groupId.toInt()
                    )
                },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onNotFound = { onError("Группа или продукты не найдены") },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            updateProducts(value ?: listOf())

            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun updateProducts(value: List<Product>) {
        _uiState.update {
            it.copy(
                products = value
            )
        }
    }

    fun resetCurrentValues() {
        _uiState.update {
            it.resetCurrentValues()
        }
    }

    private fun deleteProduct(groupId: String, productId: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }

            ResponseHandler.handle(
                request = { deleteProductUseCase(groupId, productId) },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onNotFound = { onError("Группа или продукт не найдены") },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            _uiState.update {
                it.copy(
                    products = it.products?.filter { product ->
                        product.productId.toString() != productId
                    },
                    isLoading = false
                )
            }
        }
    }

    private fun updateProduct(groupId: String, productId: String) {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        try {
            val doublePrice =
                if (_uiState.value.currentPrice == null) null
                else _uiState.value.currentPrice?.toDouble()

            viewModelScope.launch {
                ResponseHandler.handle(
                    request = {
                        updateProductUseCase(
                            groupId = groupId,
                            productId = productId,
                            request = UpdateProductRequest(
                                price = doublePrice,
                                quantity = _uiState.value.currentQuantity,
                                status = if (_uiState.value.isCurrentBought) "closed" else "open"
                            )
                        )
                    },
                    onBadRequest = { onBadRequest() },
                    onUnauthorized = { getTokenUseCase() },
                    onNotFound = { onError("Группа или продукт не найдены") },
                    onUnprocessableEntity = { onError("Неверный формат данных") },
                    onInternalServerError = { onError("На сервере произошла ошибка") },
                    onUnknownError = { onError("Не удалось подключиться к серверу") }
                )

                loadProducts()
            }
        } catch (e: NumberFormatException) {
            Log.e("error", e.message!!)

            _uiState.update {
                it.copy(
                    isCurrentPriceInvalid = true,
                    currentPriceErrorMessage = "Введите число"
                )
            }
        }

        _uiState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    private fun updateBuyWindowVisibility(value: Boolean) {
        _uiState.update {
            it.copy(
                isBuyWindowHidden = value
            )
        }
    }

    private fun updateCurrentPrice(value: String) {
        val doubleValue = if (value.isEmpty()) 0.0 else value.toDouble()
        _uiState.update {
            it.copy(
                currentPrice = value,
                isCurrentPriceInvalid = doubleValue < 0
            )
        }
    }

    private fun updateCurrentProductId(value: String) {
        _uiState.update {
            it.copy(
                currentProductId = value
            )
        }
    }

    private fun updateCurrentProductBought(value: Boolean) {
        _uiState.update {
            it.copy(
                isCurrentBought = value
            )
        }
    }

    private fun updateCurrentQuantity(value: Int) {
        _uiState.update {
            it.copy(
                currentQuantity = value
            )
        }
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