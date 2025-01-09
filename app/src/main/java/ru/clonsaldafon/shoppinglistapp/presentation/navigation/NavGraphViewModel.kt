package ru.clonsaldafon.shoppinglistapp.presentation.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.db.UserDAO
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import javax.inject.Inject

@HiltViewModel
class NavGraphViewModel @Inject constructor(
    private val dao: UserDAO
) : ViewModel() {

    private val _token = MutableLiveData<TokenResponse?>(null)
    val token: LiveData<TokenResponse?>
        get() = _token

    init {
        viewModelScope.launch {
            _token.postValue(dao.getUser())
        }
    }

}