package uz.developers.asaxiybooks.presenter.viewModel

import kotlinx.coroutines.flow.Flow
import uz.developers.asaxiybooks.data.model.MyBooksData

interface MyBooksVM {
    fun getBooks():Flow<Result<List<MyBooksData>>>
}