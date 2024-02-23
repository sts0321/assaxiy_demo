package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.domain.BooksRepository
import uz.developers.asaxiybooks.presenter.viewModel.MyBooksVM
import javax.inject.Inject


@HiltViewModel
class MyBooksVMImpl @Inject constructor(
    val booksRepository: BooksRepository
):ViewModel(),MyBooksVM {
    override fun getBooks(): Flow<Result<List<MyBooksData>>> = callbackFlow{
        booksRepository.getBooksFromUser().onEach {
            trySend(Result.success(it))
        }.launchIn(viewModelScope)
        awaitClose()
    }
}