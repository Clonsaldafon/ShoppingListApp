package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.join

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.group.JoinToGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.domain.group.JoinToGroupUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class JoinToGroupViewModel @Inject constructor(
    private val joinToGroupUseCase: JoinToGroupUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JoinToGroupUiState())
    val uiState: StateFlow<JoinToGroupUiState>
        get() = _uiState

    fun onEvent(event: JoinToGroupEvent) {
        when (event) {
            is JoinToGroupEvent.OnCodeChanged -> updateCode(event.value)
            is JoinToGroupEvent.OnSubmit -> join(event.code, event.onComplete)
        }
    }

    fun setCodeErrorMessage(message: String) {
        _uiState.update {
            it.copy(
                codeErrorMessage = message,
                isCodeInvalid = true
            )
        }
    }

    private fun updateCode(value: String) {
        _uiState.update {
            it.copy(
                code = value
            )
        }
    }

    private fun join(
        code: String,
        onComplete: (isSuccess: Boolean?,
                     codeErrorMessage: String?) -> Unit
    ) {
        _uiState.update {
            it.copy(
                isSubmitting = true
            )
        }

        viewModelScope.launch {
            try {
                val response = joinToGroupUseCase(JoinToGroupRequest(code = code)).toUiState()

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
                                    is UiState.Success -> join(code, onComplete)
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
                    _uiState.value.codeErrorMessage
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