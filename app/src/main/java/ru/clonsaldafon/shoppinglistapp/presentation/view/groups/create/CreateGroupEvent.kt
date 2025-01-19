package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.create

sealed class CreateGroupEvent {
    data class OnNameChanged(val value: String) : CreateGroupEvent()
    data class OnDescriptionChanged(val value: String) : CreateGroupEvent()
    data class OnSubmit(
        val onComplete: (isSuccess: Boolean?,
                         nameErrorMessage: String?,
                         descriptionErrorMessage: String?) -> Unit
    ) : CreateGroupEvent()
}