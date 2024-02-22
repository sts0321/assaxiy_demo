package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.developers.asaxiybooks.data.model.CategoryBooksData
import uz.developers.asaxiybooks.data.model.TypeEnum
import uz.developers.asaxiybooks.domain.AppRepository
import uz.developers.asaxiybooks.presenter.viewModel.LibraryVM
import javax.inject.Inject

@HiltViewModel
class LibraryVMImpl @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel(), LibraryVM {


    override fun getCategoryBooksData(type: TypeEnum): Flow<Result<List<CategoryBooksData>>> =
        callbackFlow{
            val data=ArrayList<CategoryBooksData>()
        appRepository.getCategoryBooks().onEach { result->
           result.onSuccess { list->
               val size=list.size
               var index=0
               list.forEach {

                   appRepository.getBooksInCategory(it.second,type).onEach { res->
                       res.onSuccess { myBooks->
                           index++
                           data.add(CategoryBooksData(it.second,it.first,myBooks))
                           if (size==index){
                               trySend(Result.success(data))
                           }
                       }
                       res.onFailure {
                           trySend(Result.failure(it))
                       }


                   }

               }
           }
            result.onFailure {
                trySend(Result.failure(it))
            }


        }.launchIn(viewModelScope)
            awaitClose()
    }

}