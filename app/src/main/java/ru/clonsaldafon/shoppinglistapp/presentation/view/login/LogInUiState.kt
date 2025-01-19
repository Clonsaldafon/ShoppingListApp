package ru.clonsaldafon.shoppinglistapp.presentation.view.login

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

data class LogInUiState(
    val login: String = "",
    val password: String = "",
    val remember: Boolean = false,
    val isLoginInvalid: Boolean = false,
    val isPasswordInvalid: Boolean = false,
    val isPasswordHidden: Boolean = true,
    val loginErrorMessage: String = "",
    val passwordErrorMessage: String = "",
    val isSubmitting: Boolean = false,
    val tokenResponse: TokenResponse? = null,
    val error: String = ""
) {

    fun reset() = copy(
        login = "",
        password = "",
        remember = false,
        isPasswordHidden = true,
        loginErrorMessage = "",
        passwordErrorMessage = "",
        isSubmitting = false,
        tokenResponse = null,
        error = ""
    )

    val isValid: Boolean
        get() = !isLoginInvalid &&
                !isPasswordInvalid &&
                login.isNotEmpty() &&
                password.isNotEmpty()

}
