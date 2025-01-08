package ru.clonsaldafon.shoppinglistapp.presentation.view.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

) : ViewModel() {

    private val _pageIndex = MutableLiveData(0)
    val pageIndex: LiveData<Int>
        get() = _pageIndex

    fun increaseIndex() {
        _pageIndex.postValue(_pageIndex.value?.plus(1))
    }

    fun decreaseIndex() {
        _pageIndex.postValue(_pageIndex.value?.minus(1))
    }

}