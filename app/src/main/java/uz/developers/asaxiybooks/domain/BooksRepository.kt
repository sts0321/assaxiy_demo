package uz.developers.asaxiybooks.domain

import kotlinx.coroutines.flow.Flow
import uz.developers.asaxiybooks.data.model.MyBooksData

interface BooksRepository {
    fun getBooksFromUser(): Flow<List<MyBooksData>>
    fun setBookFromUser(bookId:String):Flow<Result<Unit>>
}