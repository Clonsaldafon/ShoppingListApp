package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.group.UpdateProductRequest
import ru.clonsaldafon.shoppinglistapp.domain.group.GetProductsUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.DeleteProductUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.UpdateProductUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val getTokenUseCase: GetTokenUseCase
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
            try {
                val response = getProductsUseCase(_uiState.value.groupId.toInt()).toUiState()

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

    fun resetCurrentValues() {
        _uiState.update {
            it.resetCurrentValues()
        }
    }

    private fun deleteProduct(groupId: String, productId: String) {
        viewModelScope.launch {
            try {
                when (val response = deleteProductUseCase(groupId, productId).toUiState()) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                products = it.products?.filter { product ->
                                    product.productId.toString() != productId
                                }
                            )
                        }
                    }
                    is UiState.Failure -> {
                        when (response.code) {
                            401 -> {
                                when (val refreshResponse = getTokenUseCase().toUiState()) {
                                    is UiState.Success -> deleteProduct(groupId, productId)
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
                try {
                    val response = updateProductUseCase(
                        groupId = groupId,
                        productId = productId,
                        request = UpdateProductRequest(
                            price = doublePrice,
                            quantity = _uiState.value.currentQuantity,
                            status = if (_uiState.value.isCurrentBought) "closed" else "open"
                        )
                    ).toUiState()

                    when (response) {
                        is UiState.Success -> {
                            loadProducts()
                        }
                        is UiState.Failure -> {
                            when (response.code) {
                                401 -> {
                                    when (val refreshResponse = getTokenUseCase().toUiState()) {
                                        is UiState.Success ->
                                            updateProduct(groupId, productId)
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
                    resetCurrentValues()
                }
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
    }

    private fun updateBuyWindowVisibility(value: Boolean) {
        _uiState.update {
            it.copy(
                isBuyWindowHidden = value
            )
        }
    }

    private fun updateCurrentPrice(value: String) {
        _uiState.update {
            it.copy(
                currentPrice = value
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

}