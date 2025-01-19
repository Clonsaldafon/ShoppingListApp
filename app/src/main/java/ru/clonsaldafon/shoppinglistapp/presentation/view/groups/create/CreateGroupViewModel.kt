package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupRequest
import ru.clonsaldafon.shoppinglistapp.domain.group.CreateGroupUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.ResponseHandler
import javax.inject.Inject

@HiltViewModel
class CreateGroupViewModel @Inject constructor(
    private val createGroupUseCase: CreateGroupUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateGroupUiState())
    val uiState: StateFlow<CreateGroupUiState>
        get() = _uiState

    fun onEvent(event: CreateGroupEvent) {
        when (event) {
            is CreateGroupEvent.OnNameChanged -> updateName(event.value)
            is CreateGroupEvent.OnDescriptionChanged -> updateDescription(event.value)
            is CreateGroupEvent.OnSubmit -> create(event.onComplete)
        }
    }

    fun setNameErrorMessage(message: String) {
        _uiState.update {
            it.copy(
                nameErrorMessage = message,
                isNameInvalid = true
            )
        }
    }

    fun setDescriptionErrorMessage(message: String) {
        _uiState.update {
            it.copy(
                descriptionErrorMessage = message,
                isDescriptionInvalid = true
            )
        }
    }

    private fun updateName(value: String) {
        _uiState.update {
            it.copy(
                name = value,
                isNameInvalid = value.length < 3 || value.length > 30
            )
        }
    }

    private fun updateDescription(value: String) {
        _uiState.update {
            it.copy(
                description = value,
                isDescriptionInvalid = value.length > 255
            )
        }
    }

    private fun create(
        onComplete: (isSuccess: Boolean?,
                     nameErrorMessage: String?,
                     descriptionErrorMessage: String?) -> Unit
    ) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isSubmitting = true
                )
            }

            ResponseHandler.handle(
                request = {
                    createGroupUseCase(
                        CreateGroupRequest(
                            name = _uiState.value.name,
                            description = _uiState.value.description
                        )
                    )
                },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            _uiState.update {
                it.copy(
                    isSuccess = true,
                    isSubmitting = false
                )
            }

            onComplete(
                _uiState.value.isSuccess,
                _uiState.value.nameErrorMessage,
                _uiState.value.descriptionErrorMessage
            )
        }
    }

    private fun onBadRequest() {
        viewModelScope.launch {
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