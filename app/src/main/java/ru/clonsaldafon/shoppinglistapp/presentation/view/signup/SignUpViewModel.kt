package ru.clonsaldafon.shoppinglistapp.presentation.view.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.domain.SignUpUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
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
            is SignUpEvent.OnPasswordVisibilityChanged -> updatePasswordVisibility(event.value)
            is SignUpEvent.OnSubmit -> signup(
                username = event.username,
                password = event.password,
                gender = event.gender,
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

    private fun updatePasswordVisibility(value: Boolean) {
        _uiState.update {
            it.copy(
                isPasswordHidden = value
            )
        }
    }

    private fun signup(
        username: String,
        password: String,
        gender: String,
        onComplete: (tokenResponse: TokenResponse?, loginErrorMessage: String?) -> Unit
    ) {
        _uiState.update {
            it.copy(
                isSubmitting = true
            )
        }

        viewModelScope.launch {
            try {
                val response = signUpUseCase(
                    SignUpRequest(
                        username = username,
                        password = password,
                        gender =
                        if (gender == "Мужчина" || gender == "Male" || gender == "")
                            "MALE"
                        else
                            "FEMALE"
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
                        _uiState.update {
                            it.copy(
                                loginErrorMessage =
                                when (response.code) {
                                    409 -> "Такой пользователь уже существует"
                                    422 -> "Логин не должен содержать спецсимволы"
                                    else -> ""
                                }
                            )
                        }
                    }
                    is UiState.Loading -> {}
                }
            } catch (e: Exception) {
                Log.e("error", e.message!!)
            } finally {
                onComplete(
                    _uiState.value.tokenResponse,
                    _uiState.value.loginErrorMessage
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