package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenDetailBinding
import uz.developers.asaxiybooks.presenter.viewModel.BookDetailViewModel
import uz.developers.asaxiybooks.presenter.viewModel.impl.BookDetailViewModelImpl

class BookDetailScreen : Fragment(R.layout.screen_detail) {
    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: BookDetailViewModel by viewModels<BookDetailViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}