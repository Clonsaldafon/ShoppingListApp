package ru.clonsaldafon.shoppinglistapp.presentation.view.signup

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

sealed class SignUpEvent {
    data class OnLoginChanged(val value: String) : SignUpEvent()
    data class OnPasswordChanged(val value: String) : SignUpEvent()
    data class OnGenderChanged(val value: String) : SignUpEvent()
    data class OnRememberChanged(val value: Boolean) : SignUpEvent()
    data class OnPasswordVisibilityChanged(val value: Boolean) : SignUpEvent()
    data class OnSubmit(
        val onComplete: (tokenResponse: TokenResponse?, loginErrorMessage: String?) -> Unit
    ) : SignUpEvent()
}