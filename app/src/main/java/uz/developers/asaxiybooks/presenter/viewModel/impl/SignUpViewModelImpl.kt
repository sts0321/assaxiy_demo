package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.developers.asaxiybooks.data.model.UserData
import uz.developers.asaxiybooks.domain.AppRepository
import uz.developers.asaxiybooks.presenter.viewModel.SignUpViewModel
import javax.inject.Inject

class SignUpViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(),
    SignUpViewModel {
    override fun signUpUser(userData: UserData) {
        viewModelScope.launch {
            repository.signUpUser(userData)
        }
    }
}
