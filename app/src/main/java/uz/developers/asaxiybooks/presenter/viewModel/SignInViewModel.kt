package uz.developers.asaxiybooks.presenter.viewModel

import kotlinx.coroutines.flow.SharedFlow
import uz.developers.asaxiybooks.data.model.CreateAccount

interface SignInViewModel {
    val messageFlow:SharedFlow<String>
    val successFlow:SharedFlow<Unit>

    fun createAccount(createAccount: CreateAccount)
}