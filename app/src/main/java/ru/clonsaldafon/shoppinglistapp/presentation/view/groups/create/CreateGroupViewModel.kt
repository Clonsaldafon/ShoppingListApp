package ru.clonsaldafon.shoppinglistapp.presentation.view.groups.create

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.domain.group.CreateGroupUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class CreateGroupViewModel @Inject constructor(
    private val createGroupUseCase: CreateGroupUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateGroupUiState())
    val uiState: StateFlow<CreateGroupUiState>
        get() = _uiState

    fun onEvent(event: CreateGroupEvent) {
        when (event) {
            is CreateGroupEvent.OnNameChanged -> updateName(event.value)
            is CreateGroupEvent.OnDescriptionChanged -> updateDescription(event.value)
            is CreateGroupEvent.OnSubmit -> create(event.name, event.description, event.onComplete)
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
        name: String,
        description: String,
        onComplete: (isSuccess: Boolean?,
                     nameErrorMessage: String?,
                     descriptionErrorMessage: String?) -> Unit
    ) {
        _uiState.update {
            it.copy(
                isSubmitting = true
            )
        }

        viewModelScope.launch {
            try {
                val response = createGroupUseCase(
                    CreateGroupRequest(
                        name = name,
                        description = description
                    )
                ).toUiState()

                when (response) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                isSuccess = true
                            )
                        }
                    }
                    is UiState.Failure -> {
                        when (response.code) {
                            401 -> {
                                when (val refreshResponse = getTokenUseCase().toUiState()) {
                                    is UiState.Success -> create(name, description, onComplete)
                                    is UiState.Failure -> {}
                                    is UiState.Loading -> {}
                                }
                            }
                        }
                    }
                    is UiState.Loading -> {}
                }
            } catch (e: Exception) {
                Log.e("error", e.message!!)
            } finally {
                onComplete(
                    _uiState.value.isSuccess,
                    _uiState.value.nameErrorMessage,
                    _uiState.value.descriptionErrorMessage
                )

                _uiState.update {
                    it.copy(
                        isSubmitting = false
                    )
                }
            }
        }
    }

}