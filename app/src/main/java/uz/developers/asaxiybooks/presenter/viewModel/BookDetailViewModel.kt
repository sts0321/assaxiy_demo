package uz.developers.asaxiybooks.presenter.viewModel

import kotlinx.coroutines.flow.Flow

interface BookDetailViewModel {
    fun setBookInUser(bookId:String):Flow<Result<Unit>>
}