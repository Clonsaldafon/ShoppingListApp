package ru.clonsaldafon.shoppinglistapp.presentation.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.ResponseHandler
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
            is LogInEvent.OnRememberChanged -> updateRemember(event.value)
            is LogInEvent.OnPasswordVisibilityChanged -> updatePasswordVisibility(event.value)
            is LogInEvent.OnSubmit -> login(event.onComplete)
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
                login = value,
                isLoginInvalid = value.length < 3 || value.length > 30
            )
        }
    }

    private fun updatePassword(value: String) {
        _uiState.update {
            it.copy(
                password = value,
                isPasswordInvalid = value.length < 8
            )
        }
    }

    private fun updateRemember(value: Boolean) {
        _uiState.update {
            it.copy(
                remember = value
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
        onComplete: (tokenResponse: TokenResponse?,
                     loginErrorMessage: String?,
                     passwordErrorMessage: String?) -> Unit
    ) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isSubmitting = true
                )
            }

            val value = ResponseHandler.handle(
                request = {
                    logInUseCase(
                        request = LogInRequest(
                            username = _uiState.value.login,
                            password = _uiState.value.password
                        ),
                        remember = _uiState.value.remember
                    )
                },
                onBadRequest = { onBadRequest() },
                onNotFound = { onNotFound() },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            updateTokens(value?.accessToken, value?.refreshToken)

            _uiState.update {
                it.copy(
                    isSubmitting = false
                )
            }

            onComplete(
                _uiState.value.tokenResponse,
                _uiState.value.loginErrorMessage,
                _uiState.value.passwordErrorMessage
            )
        }
    }

    private fun updateTokens(accessToken: String?, refreshToken: String?) {
        _uiState.update {
            it.copy(
                tokenResponse = TokenResponse(
                    accessToken = accessToken,
                    refreshToken = refreshToken
                )
            )
        }
    }

    private fun onBadRequest() {
        _uiState.update {
            it.copy(
                passwordErrorMessage = "Неверный пароль",
                isPasswordInvalid = true
            )
        }
    }

    private fun onNotFound() {
        _uiState.update {
            it.copy(
                loginErrorMessage = "Такого пользователя не существует",
                isLoginInvalid = true
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