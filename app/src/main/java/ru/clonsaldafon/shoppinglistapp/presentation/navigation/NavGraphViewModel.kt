package ru.clonsaldafon.shoppinglistapp.presentation.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.db.UserDAO
import ru.clonsaldafon.shoppinglistapp.data.db.UserEntity
import javax.inject.Inject

@HiltViewModel
class NavGraphViewModel @Inject constructor(
    private val dao: UserDAO
) : ViewModel() {

    private val _userEntity = MutableLiveData<List<UserEntity>?>(null)
    val userEntity: LiveData<List<UserEntity>?>
        get() = _userEntity

    init {
        viewModelScope.launch {
            val user = dao.getUser()
            if (user != null && user.size > 1)
                dao.clear()

            _userEntity.postValue(dao.getUser())
        }
    }

}