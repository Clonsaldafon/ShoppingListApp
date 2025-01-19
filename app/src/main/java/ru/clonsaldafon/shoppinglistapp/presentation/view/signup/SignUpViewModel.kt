package ru.clonsaldafon.shoppinglistapp.presentation.view.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.domain.user.SignUpUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.ResponseHandler
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState>
        get() = _uiState

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnLoginChanged -> updateLogin(event.value)
            is SignUpEvent.OnPasswordChanged -> updatePassword(event.value)
            is SignUpEvent.OnGenderChanged -> updateGender(event.value)
            is SignUpEvent.OnRememberChanged -> updateRemember(event.value)
            is SignUpEvent.OnPasswordVisibilityChanged -> updatePasswordVisibility(event.value)
            is SignUpEvent.OnSubmit -> signup(event.onComplete)
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

    private fun updateGender(value: String) {
        _uiState.update {
            it.copy(
                gender = value
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

    private fun signup(
        onComplete: (tokenResponse: TokenResponse?, loginErrorMessage: String?) -> Unit
    ) {
        _uiState.update {
            it.copy(
                isSubmitting = true
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            val gender =
                if (_uiState.value.gender == "Мужчина" ||
                    _uiState.value.gender == "Male" ||
                    _uiState.value.gender == "")
                    "MALE"
                else
                    "FEMALE"

            val value = ResponseHandler.handle(
                request = {
                    signUpUseCase(
                        request = SignUpRequest(
                            username = _uiState.value.login,
                            password = _uiState.value.password,
                            gender = gender
                        ),
                        remember = _uiState.value.remember
                    )
                },
                onConflict = { onConflict() },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            updateTokens(value?.accessToken, value?.refreshToken)

            onComplete(
                _uiState.value.tokenResponse,
                _uiState.value.loginErrorMessage
            )
        }

        _uiState.update {
            it.copy(
                isSubmitting = false
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

    private fun onConflict() {
        _uiState.update {
            it.copy(
                loginErrorMessage = "Такой пользователь уже существует",
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