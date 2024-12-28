package ru.clonsaldafon.shoppinglistapp.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User
import ru.clonsaldafon.shoppinglistapp.domain.SignUpUseCase
import ru.clonsaldafon.shoppinglistapp.presentation.UiState
import ru.clonsaldafon.shoppinglistapp.presentation.toUiState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<User?>>()
    val uiState: LiveData<UiState<User?>>
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

    fun signup() {
        viewModelScope.launch {
            _uiState.postValue(UiState.Loading)
            val request = SignUpRequest(_login.value, _password.value)
            _uiState.postValue(signUpUseCase(request).toUiState())
        }
    }

}