package ru.clonsaldafon.shoppinglistapp.presentation

import android.util.Log

object ResponseHandler {

    suspend inline fun <T> handle(
        crossinline request: suspend () -> Result<T>,
        noinline onBadRequest: suspend () -> Unit = {},
        noinline onUnauthorized: suspend () -> Unit = {},
        noinline onForbidden: suspend () -> Unit = {},
        noinline onNotFound: suspend () -> Unit = {},
        noinline onConflict: suspend () -> Unit = {},
        noinline onUnprocessableEntity: suspend () -> Unit = {},
        noinline onInternalServerError: suspend () -> Unit = {},
        noinline onUnknownError: suspend () -> Unit = {}
    ) : T? =
        try {
            when (val result = request().toUiState()) {
                is UiState.Success -> result.value
                is UiState.Failure -> {
                    when (result.code) {
                        400 -> onBadRequest()
                        401 -> onUnauthorized()
                        403 -> onForbidden()
                        404 -> onNotFound()
                        409 -> onConflict()
                        422 -> onUnprocessableEntity()
                        500 -> onInternalServerError()
                        520 -> onUnknownError()
                    }
                    null
                }
                else -> null
            }
        } catch (e: Exception) {
            Log.e("error", e.message!!)
            null
        }

}