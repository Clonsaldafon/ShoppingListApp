package ru.clonsaldafon.shoppinglistapp.presentation.view.signup

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

data class SignUpUiState(
    val login: String = "",
    val password: String = "",
    val gender: String = "",
    val remember: Boolean = false,
    val isLoginInvalid: Boolean = false,
    val isPasswordInvalid: Boolean = false,
    val isPasswordHidden: Boolean = true,
    val loginErrorMessage: String = "",
    val isSubmitting: Boolean = false,
    val tokenResponse: TokenResponse? = null,
    val error: String = ""
) {

    fun reset() = copy(
        login = "",
        password = "",
        gender = "",
        remember = false,
        isLoginInvalid = false,
        isPasswordInvalid = false,
        isPasswordHidden = true,
        loginErrorMessage = "",
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
