package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.developers.asaxiybooks.data.model.CreateAccount
import uz.developers.asaxiybooks.domain.AppRepository
import uz.developers.asaxiybooks.presenter.viewModel.SignInViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), SignInViewModel {

    override val messageFlow= MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val successFlow =MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun createAccount(createAccount: CreateAccount) {
        repository.createAccount(createAccount)
            .onEach {
                it.onSuccess {
                    messageFlow.tryEmit("Success Create Account")
                    successFlow.tryEmit(Unit)
                }
                it.onFailure {
                    messageFlow.tryEmit("Error ${it.message}")
                }

            }
            .launchIn(viewModelScope)

    }
}