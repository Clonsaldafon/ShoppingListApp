package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.create

data class CreateGroupUiState(
    val name: String = "",
    val description: String = "",
    val isNameInvalid: Boolean = false,
    val isDescriptionInvalid: Boolean = false,
    val nameErrorMessage: String = "",
    val descriptionErrorMessage: String = "",
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false
) {

    fun reset() = copy(
        name = "",
        description = "",
        isNameInvalid = false,
        isDescriptionInvalid = false,
        nameErrorMessage = "",
        descriptionErrorMessage = "",
        isSubmitting = false,
        isSuccess = false
    )

    val isValid: Boolean
        get() = !isNameInvalid &&
                !isDescriptionInvalid &&
                name.isNotEmpty() &&
                description.isNotEmpty()

}
