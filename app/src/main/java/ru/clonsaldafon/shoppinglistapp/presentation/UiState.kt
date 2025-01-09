package ru.clonsaldafon.shoppinglistapp.presentation

import ru.clonsaldafon.shoppinglistapp.data.model.ApiException

sealed class UiState<out T> {
    class Success<T>(val value: T) : UiState<T>()
    class Failure(val message: String, val code: Int) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}

fun <T> Result<T>.toUiState(): UiState<T> =
    if (this.isSuccess) {
        val result = this.getOrNull()
        result?.let { UiState.Success(it) }
            ?: UiState.Failure("Data was null", 404)
    } else this.exceptionOrNull()?.let {
        when (it) {
            is ApiException -> UiState.Failure(it.message, it.code)
            else -> UiState.Failure("Unknown error", 520)
        }
    } ?: UiState.Failure("Unknown error", 520)