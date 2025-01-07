package ru.clonsaldafon.shoppinglistapp.presentation.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.domain.LogInUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<TokenResponse?>>()
    val uiState: LiveData<UiState<TokenResponse?>>
        get() = _uiState

    private val _login = MutableLiveData("")
    val login: LiveData<String>
        get() = _login

    private val _password = MutableLiveData("")
    val password: LiveData<String>
        get() = _password

    fun onLoginChanged(value: String) {
        _login.postValue(value)
    }

    fun onPasswordChanged(value: String) {
        _password.postValue(value)
    }

    fun login() {
        viewModelScope.launch {
            _uiState.postValue(UiState.Loading)

            val request = LogInRequest(
                requireNotNull(_login.value),
                requireNotNull(_password.value)
            )

            _uiState.postValue(logInUseCase(request).toUiState())
        }
    }

}