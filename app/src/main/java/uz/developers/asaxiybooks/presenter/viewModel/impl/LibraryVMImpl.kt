package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasiyaapp.utils.myLog
import com.example.uzummarketclient.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.model.CategoryBooksData
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.data.model.TypeEnum
import uz.developers.asaxiybooks.domain.AppRepository
import uz.developers.asaxiybooks.presenter.screen.HomeScreenDirections
import uz.developers.asaxiybooks.presenter.viewModel.LibraryVM
import javax.inject.Inject

@HiltViewModel
class LibraryVMImpl @Inject constructor(
    private val appRepository: AppRepository,
    private val appNavigator: AppNavigator
) : ViewModel(), LibraryVM {


    override fun getCategoryBooksData(): Flow<Result<List<CategoryBooksData>>> =
        callbackFlow{

            val data=ArrayList<CategoryBooksData>()
        appRepository.getCategoryBooks().onEach { result->
           result.onSuccess { list->
               val size=list.size
               var index=0
               list.forEach {
                   appRepository.getBooksInCategory(it.first,TypeEnum.PDF).onEach { res->
                       res.onSuccess { myBooks->
                           index++
                           if (myBooks.size!=0){
                               data.add(CategoryBooksData(it.first,it.second,myBooks))
                           }
                           if (size==index){
                               trySend(Result.success(data))
                           }
                       }
                       res.onFailure {
                           trySend(Result.failure(it))
                       }


                   }.launchIn(viewModelScope)

               }
           }
            result.onFailure {
                trySend(Result.failure(it))
            }


        }.launchIn(viewModelScope)
            awaitClose()
    }

    override  fun onClickItem(name: String, link: String) {
        viewModelScope.launch {
//            appNavigator.navigateTo(HomeScreenDirections.actionHomeScreenToReadBookScreen())
//            appNavigator.navigateTo(R.id.action_homeScreen_to_readBookScreen)
        }
    }

    override fun onClickAll(data: List<MyBooksData>) {

    }

}