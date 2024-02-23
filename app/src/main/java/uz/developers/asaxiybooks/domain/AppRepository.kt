package uz.developers.asaxiybooks.domain

import kotlinx.coroutines.flow.Flow
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.data.model.TypeEnum
import uz.developers.asaxiybooks.data.model.UserData

interface AppRepository {
    fun getAllBooks(type:TypeEnum):Flow<Result<List<MyBooksData>>>
    suspend fun signUpUser(userData: UserData)
    fun getCategoryBooks():Flow<Result<List<Pair<String,String>>>>
    fun getBooksInCategory(categoryId:String,type: TypeEnum):Flow<Result<List<MyBooksData>>>
}