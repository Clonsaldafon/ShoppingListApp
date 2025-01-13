package ru.clonsaldafon.shoppinglistapp.presentation.view.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.domain.user.ExitUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val exitUseCase: ExitUseCase
) : ViewModel() {

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnExit -> exit(event.onComplete)
        }
    }

    private fun exit(
        onComplete: (isSuccess: Boolean?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = exitUseCase().toUiState()

                when (response) {
                    is UiState.Success -> onComplete(true)
                    is UiState.Failure -> onComplete(false)
                    is UiState.Loading -> {}
                }
            } catch (e: Exception) {
                Log.e("error", e.message!!)
            }
        }
    }

}