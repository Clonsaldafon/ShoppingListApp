package ru.clonsaldafon.shoppinglistapp.presentation.view.products.group

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.Member
import ru.clonsaldafon.shoppinglistapp.domain.group.GetMembersUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.ResponseHandler
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class GroupInfoViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GroupInfoUiState())
    val uiState: StateFlow<GroupInfoUiState>
        get() = _uiState

    fun loadMembers(groupId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val value = ResponseHandler.handle(
                request = { getMembersUseCase(groupId) },
                onBadRequest = { onBadRequest() },
                onUnauthorized = { getTokenUseCase() },
                onForbidden = { onError("У вас нет прав на выполнение этой операции") },
                onNotFound = { onError("Группа или продукты не найдены") },
                onUnprocessableEntity = { onError("Неверный формат данных") },
                onInternalServerError = { onError("На сервере произошла ошибка") },
                onUnknownError = { onError("Не удалось подключиться к серверу") }
            )

            updateMembers(value)
        }

        _uiState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    private fun updateMembers(value: List<Member>?) {
        _uiState.update {
            it.copy(
                members = value
            )
        }
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