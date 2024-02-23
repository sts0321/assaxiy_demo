package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.developers.asaxiybooks.domain.BooksRepository
import uz.developers.asaxiybooks.presenter.viewModel.BookDetailViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModelImpl @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel(), BookDetailViewModel {
    override fun setBookInUser(bookId: String): Flow<Result<Unit>> = callbackFlow {
        booksRepository.setBookFromUser(bookId)



    }
}