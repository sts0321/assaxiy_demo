package uz.developers.asaxiybooks.domain

import kotlinx.coroutines.flow.Flow
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.data.model.TypeEnum

interface AppRepository {
    fun getAllBooks(type:TypeEnum):Flow<Result<List<MyBooksData>>>

    fun getCategoryBooks():Flow<Result<List<Pair<String,String>>>>
    fun getBooksInCategory(categoryId:String):Flow<Result<List<MyBooksData>>>
}