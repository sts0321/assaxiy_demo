package uz.developers.asaxiybooks.domain

import kotlinx.coroutines.flow.Flow
import uz.developers.asaxiybooks.data.model.CreateAccount
import uz.developers.asaxiybooks.data.model.LogInData
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.data.model.TypeEnum

interface AppRepository {
    fun getAllBooks(type:TypeEnum):Flow<Result<List<MyBooksData>>>

    fun getCategoryBooks():Flow<Result<List<Pair<String,String>>>>
    fun getBooksInCategory(categoryId:String,type: TypeEnum):Flow<Result<List<MyBooksData>>>

    fun logIn(logInData: LogInData):Flow<Result<Unit>>

    fun createAccount(createAccount: CreateAccount):Flow<Result<Unit>>
}