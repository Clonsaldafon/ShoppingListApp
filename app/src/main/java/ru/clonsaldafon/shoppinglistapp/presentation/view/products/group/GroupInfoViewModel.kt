package ru.clonsaldafon.shoppinglistapp.presentation.view.products.group

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.domain.group.GetMembersUseCase
import ru.clonsaldafon.shoppinglistapp.domain.user.GetTokenUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class GroupInfoViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GroupInfoUiState())
    val uiState: StateFlow<GroupInfoUiState>
        get() = _uiState

    fun loadMembers(groupId: Int) {
        viewModelScope.launch {
            try {
                when (val response = getMembersUseCase(groupId).toUiState()) {
                    is UiState.Success -> {
                        _uiState.update {
                            it.copy(
                                members = response.value
                            )
                        }
                    }
                    is UiState.Failure -> {
                        when (response.code) {
                            401 -> {
                                when (val refreshResponse = getTokenUseCase().toUiState()) {
                                    is UiState.Success -> loadMembers(groupId)
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

}