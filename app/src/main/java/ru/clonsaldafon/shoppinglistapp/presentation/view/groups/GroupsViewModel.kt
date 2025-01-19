package ru.clonsaldafon.shoppinglistapp.presentation.view.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.clonsaldafon.shoppinglistapp.data.model.Group
import ru.clonsaldafon.shoppinglistapp.domain.user.GetGroupsUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.ResponseHandler
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val getGroupsUseCase: GetGroupsUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GroupsUiState())
    val uiState: StateFlow<GroupsUiState>
        get() = _uiState

    init {
        loadGroups()
    }

    fun onEvent(event: GroupsEvent) {
        when (event) {
            is GroupsEvent.OnGroupsLoaded -> updateGroupList(event.value)
        }
    }

    private fun loadGroups() {
        viewModelScope.launch {
            val value = ResponseHandler.handle(
                request = { getGroupsUseCase() },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            updateGroupList(value ?: listOf())

            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun updateGroupList(value: List<Group>) {
        _uiState.update {
            it.copy(
                groups = value
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