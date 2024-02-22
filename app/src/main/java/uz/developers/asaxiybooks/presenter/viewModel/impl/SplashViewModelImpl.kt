package uz.developers.asaxiybooks.presenter.viewModel.impl

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.developers.asaxiybooks.data.sourse.Pref
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(private val pref: Pref) : ViewModel() {

    val navigateToScreen = MutableLiveData<Boolean>()

    init {
        determineInitialScreen()
    }

    private fun determineInitialScreen() {
        viewModelScope.launch {
            delay(1500)
            val isFirstTime = pref.isFirstTime()
            if (isFirstTime) {
                navigateToScreen.value = true
                pref.setFirstTime(false)
            } else {
                navigateToScreen.value = false
            }
        }
    }
}
