package ru.clonsaldafon.shoppinglistapp.presentation.view.login

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

sealed class LogInEvent {
    data class OnLoginChanged(val value: String) : LogInEvent()
    data class OnPasswordChanged(val value: String) : LogInEvent()
    data class OnPasswordVisibilityChanged(val value: Boolean) : LogInEvent()
    data class OnSubmit(
        val username: String,
        val password: String,
        val onComplete: (tokenResponse: TokenResponse?,
                         loginErrorMessage: String?,
                         passwordErrorMessage: String?) -> Unit
    ) : LogInEvent()
}