package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.join

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.group.JoinToGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.domain.group.JoinToGroupUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.ResponseHandler
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class JoinToGroupViewModel @Inject constructor(
    private val joinToGroupUseCase: JoinToGroupUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JoinToGroupUiState())
    val uiState: StateFlow<JoinToGroupUiState>
        get() = _uiState

    fun onEvent(event: JoinToGroupEvent) {
        when (event) {
            is JoinToGroupEvent.OnCodeChanged -> updateCode(event.value)
            is JoinToGroupEvent.OnSubmit -> join(event.onComplete)
        }
    }

    fun setCodeErrorMessage(message: String) {
        _uiState.update {
            it.copy(
                codeErrorMessage = message,
                isCodeInvalid = true
            )
        }
    }

    private fun updateCode(value: String) {
        _uiState.update {
            it.copy(
                code = value
            )
        }
    }

    private fun join(
        onComplete: (isSuccess: Boolean?,
                     codeErrorMessage: String?) -> Unit
    ) {
        _uiState.update {
            it.copy(
                isSubmitting = true
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            ResponseHandler.handle(
                request = {
                    joinToGroupUseCase(
                        JoinToGroupRequest(_uiState.value.code)
                    )
                },
//                onBadRequest = { TODO("refresh token expired or incorrect code") },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onConflict = { onError("Вы уже состоите в этой группе") },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )
        }

        _uiState.update {
            it.copy(
                isSuccess = true,
                isSubmitting = false
            )
        }

        onComplete(
            _uiState.value.isSuccess,
            _uiState.value.codeErrorMessage
        )
    }

    private fun onBadRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            ResponseHandler.handle(
                request = { logInUseCase() },
                onBadRequest = { onBadRequest() },
                onNotFound = {  },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )
        }
    }

    private fun onError(message: String) {
        _uiState.update {
            it.copy(
                error = message
            )
        }
    }

}