package uz.developers.asaxiybooks.domain

import kotlinx.coroutines.flow.Flow
import uz.developers.asaxiybooks.data.model.MyBooksData

interface AppRepository {
    fun getAllBooks():Flow<MyBooksData>
}