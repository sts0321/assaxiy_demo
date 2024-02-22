package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        initView()
    }

    private fun initView() = binding.apply {
        rvListOuter.adapter = adapter
        rvListOuter.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getCategoryBooksData().onEach {
            it.onSuccess {
                adapter.submitList(it)
            }
            it.onFailure {
                Toast.makeText(requireContext(),it.message?:"Error!!",Toast.LENGTH_SHORT).show()
            }



        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
    }
}