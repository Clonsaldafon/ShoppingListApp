package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.join

sealed class JoinToGroupEvent {
    data class OnCodeChanged(val value: String) : JoinToGroupEvent()
    data class OnSubmit(
        val onComplete: (isSuccess: Boolean?,
                         codeErrorMessage: String?) -> Unit
    ) : JoinToGroupEvent()
}