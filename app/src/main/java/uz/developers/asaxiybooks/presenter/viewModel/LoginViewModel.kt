package uz.developers.asaxiybooks.presenter.viewModel

import kotlinx.coroutines.flow.SharedFlow
import uz.developers.asaxiybooks.data.model.LogInData

interface LoginViewModel {

    val messageFlow :SharedFlow<String>
    val successFlow:SharedFlow<Unit>

    fun loginAccount(logInData: LogInData)
}