package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenLibraryBinding
import uz.developers.asaxiybooks.presenter.adapter.LibraryScreenAdapter
import uz.developers.asaxiybooks.presenter.viewModel.LibraryVM
import uz.developers.asaxiybooks.presenter.viewModel.impl.LibraryVMImpl

@AndroidEntryPoint
class LibraryScreen : Fragment(R.layout.screen_library) {

    private val binding by viewBinding(ScreenLibraryBinding::bind)
    private val adapter = LibraryScreenAdapter()
    private val viewModel: LibraryVM by viewModels<LibraryVMImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategoryBooksData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initView() = binding.apply {
        rvListOuter.adapter = adapter
        rvListOuter.layoutManager = LinearLayoutManager(requireContext())


    }
}