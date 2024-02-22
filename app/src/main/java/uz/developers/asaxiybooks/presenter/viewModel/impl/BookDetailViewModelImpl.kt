package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.developers.asaxiybooks.presenter.viewModel.BookDetailViewModel
import uz.developers.asaxiybooks.presenter.viewModel.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModelImpl @Inject constructor() : ViewModel(), BookDetailViewModel {
}