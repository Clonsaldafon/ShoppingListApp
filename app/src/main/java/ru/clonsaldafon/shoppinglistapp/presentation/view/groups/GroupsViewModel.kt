package ru.clonsaldafon.shoppinglistapp.presentation.view.groups

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.Group
import ru.clonsaldafon.shoppinglistapp.domain.user.GetGroupsUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val getGroupsUseCase: GetGroupsUseCase,
    private val getTokenUseCase: GetTokenUseCase
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
            try {
                when (val response = getGroupsUseCase().toUiState()) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                groups = response.value
                            )
                        }
                    }
                    is UiState.Failure -> {
                        when (response.code) {
                            401 -> {
                                when (val refreshResponse = getTokenUseCase().toUiState()) {
                                    is UiState.Success -> loadGroups()
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
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun updateGroupList(value: List<Group>) {
        _uiState.update {
            it.copy(
                groups = value,
                isLoading = false
            )
        }
    }

}