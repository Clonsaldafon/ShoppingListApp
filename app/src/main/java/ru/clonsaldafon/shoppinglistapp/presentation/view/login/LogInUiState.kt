package ru.clonsaldafon.shoppinglistapp.presentation.view.login

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

data class LogInUiState(
    val login: String = "",
    val password: String = "",
    val isLoginInvalid: Boolean = false,
    val isPasswordInvalid: Boolean = false,
    val isPasswordHidden: Boolean = true,
    val loginErrorMessage: String = "",
    val passwordErrorMessage: String = "",
    val isSubmitting: Boolean = false,
    val tokenResponse: TokenResponse? = null
) {

    fun reset() = copy(
        login = "",
        password = "",
        isPasswordHidden = true,
        loginErrorMessage = "",
        passwordErrorMessage = "",
        isSubmitting = false,
        tokenResponse = null
    )

    val isValid: Boolean
        get() = login.isNotEmpty() &&
                password.isNotEmpty()

}
