package uz.developers.asaxiybooks.presenter.viewModel

import kotlinx.coroutines.flow.Flow
import uz.developers.asaxiybooks.data.model.CategoryBooksData
import uz.developers.asaxiybooks.data.model.MyBooksData

interface AudioLibraryVM {
    fun getCategoryBooksData(): Flow<Result<List<CategoryBooksData>>>
    fun onClickItem(name:String,link:String)
    fun onClickAll(data:List<MyBooksData>)
}