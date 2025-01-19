package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.join

data class JoinToGroupUiState(
    val code: String = "",
    val codeErrorMessage: String = "",
    val isCodeInvalid: Boolean = false,
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
) {

    fun reset() = copy(
        code = "",
        codeErrorMessage = "",
        isCodeInvalid = false,
        isSubmitting = false,
        isSuccess = false,
        error = ""
    )

    val isValid: Boolean
        get() = !isCodeInvalid &&
                code.isNotEmpty()

}
