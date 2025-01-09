package ru.clonsaldafon.shoppinglistapp.presentation.view.signup

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

data class SignUpUiState(
    val login: String = "",
    val password: String = "",
    val gender: String = "",
    val isLoginInvalid: Boolean = false,
    val isPasswordInvalid: Boolean = false,
    val isPasswordHidden: Boolean = true,
    val loginErrorMessage: String = "",
    val isSubmitting: Boolean = false,
    val tokenResponse: TokenResponse? = null
) {

    fun reset() = copy(
        login = "",
        password = "",
        gender = "",
        isLoginInvalid = false,
        isPasswordInvalid = false,
        isPasswordHidden = true,
        loginErrorMessage = "",
        isSubmitting = false,
        tokenResponse = null
    )

    val isValid: Boolean
        get() = !isLoginInvalid &&
                !isPasswordInvalid &&
                login.isNotEmpty() &&
                password.isNotEmpty()

}
