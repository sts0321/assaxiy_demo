package uz.developers.asaxiybooks.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.developers.asaxiybooks.domain.AppRepository
import uz.developers.asaxiybooks.presenter.viewModel.ProfileVM
import javax.inject.Inject

@HiltViewModel
class ProfileVMImpl @Inject constructor(
    appRepository: AppRepository
): ViewModel(), ProfileVM {

}