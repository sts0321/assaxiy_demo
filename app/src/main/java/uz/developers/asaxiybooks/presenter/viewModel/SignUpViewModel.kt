package uz.developers.asaxiybooks.presenter.viewModel

import uz.developers.asaxiybooks.data.model.UserData

interface SignUpViewModel {
    fun signUpUser(userData: UserData)
}