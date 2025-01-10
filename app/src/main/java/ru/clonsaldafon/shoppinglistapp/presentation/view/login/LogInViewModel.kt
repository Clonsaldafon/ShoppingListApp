package ru.clonsaldafon.shoppinglistapp.presentation.view.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LogInUiState())
    val uiState: StateFlow<LogInUiState>
        get() = _uiState

    fun onEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.OnLoginChanged -> updateLogin(event.value)
            is LogInEvent.OnPasswordChanged -> updatePassword(event.value)
            is LogInEvent.OnPasswordVisibilityChanged -> updatePasswordVisibility(event.value)
            is LogInEvent.OnSubmit -> login(
                username = event.username,
                password = event.password,
                onComplete = event.onComplete
            )
        }
    }

    fun setLoginErrorMessage(message: String) {
        _uiState.update {
            it.copy(
                loginErrorMessage = message,
                isLoginInvalid = true
            )
        }
    }

    fun setPasswordErrorMessage(message: String) {
        _uiState.update {
            it.copy(
                passwordErrorMessage = message,
                isPasswordInvalid = true
            )
        }
    }

    private fun updateLogin(value: String) {
        _uiState.update {
            it.copy(
                login = value
            )
        }
    }

    private fun updatePassword(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
    }

    private fun updatePasswordVisibility(value: Boolean) {
        _uiState.update {
            it.copy(
                isPasswordHidden = value
            )
        }
    }

    private fun login(
        username: String,
        password: String,
        onComplete: (tokenResponse: TokenResponse?,
                     loginErrorMessage: String?,
                     passwordErrorMessage: String?) -> Unit
    ) {
        _uiState.update {
            it.copy(
                isSubmitting = true
            )
        }

        viewModelScope.launch {
            try {
                val response = logInUseCase(
                    LogInRequest(
                        username = username,
                        password = password
                    )
                ).toUiState()

                when (response) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                tokenResponse = TokenResponse(
                                    accessToken = response.value?.accessToken,
                                    refreshToken = response.value?.refreshToken
                                )
                            )
                        }
                    }
                    is UiState.Failure -> {

                    }
                    is UiState.Loading -> {}
                }
            } catch (e: Exception) {
                Log.e("error", e.message!!)
            } finally {
                onComplete(
                    _uiState.value.tokenResponse,
                    _uiState.value.loginErrorMessage,
                    _uiState.value.passwordErrorMessage
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