package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasiyaapp.utils.myLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.developers.asaxiybooks.domain.BooksRepository
import uz.developers.asaxiybooks.presenter.viewModel.BookDetailViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModelImpl @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel(), BookDetailViewModel {
    override fun setBookInUser(bookId: String): Flow<Result<Unit>> = callbackFlow {
        booksRepository.setBookFromUser(bookId).onEach {
            it.onSuccess {
                "success".myLog()
                trySend(Result.success(Unit))
            }
            it.onFailure {
                trySend(Result.failure(it))
            }
        }.launchIn(viewModelScope)

        awaitClose()
    }
}