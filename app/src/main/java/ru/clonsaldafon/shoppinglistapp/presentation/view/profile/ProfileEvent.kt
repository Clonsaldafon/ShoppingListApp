package ru.clonsaldafon.shoppinglistapp.presentation.view.profile

sealed class ProfileEvent {
    data class OnExit(val onComplete: (isSuccess: Boolean?) -> Unit) : ProfileEvent()
}