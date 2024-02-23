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
    var counter=0
    override fun setBookInUser(bookId: String): Flow<Result<Unit>> = callbackFlow {
        if (counter==0){
            booksRepository.setBookFromUser(bookId).onEach {
                it.onSuccess {
                    counter++
                    "success".myLog()
                    trySend(Result.success(Unit))
                    channel.close()
                }
                it.onFailure {
                    counter++
                    trySend(Result.failure(it))
                    channel.close()
                }
            }.launchIn(viewModelScope)
        }

        awaitClose()
    }
}