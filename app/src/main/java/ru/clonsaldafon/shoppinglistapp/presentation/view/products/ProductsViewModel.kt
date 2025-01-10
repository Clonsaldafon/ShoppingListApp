package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.domain.group.GetProductsUseCase
import ru.clonsaldafon.shoppinglistapp.domain.product.DeleteProductUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState>
        get() = _uiState

    fun onEvent(event: ProductsEvent) {
        when (event) {
            is ProductsEvent.OnProductDeleted -> deleteProduct(event.groupId, event.productId)
            is ProductsEvent.OnProductsLoaded -> {}
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

}