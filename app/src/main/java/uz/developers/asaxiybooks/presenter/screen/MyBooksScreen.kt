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
import uz.developers.asaxiybooks.databinding.ScreenMybooksBinding
import uz.developers.asaxiybooks.presenter.adapter.MyBookAdapter
import uz.developers.asaxiybooks.presenter.viewModel.MyBooksVM
import uz.developers.asaxiybooks.presenter.viewModel.impl.MyBooksVMImpl

@AndroidEntryPoint
class MyBooksScreen:Fragment(R.layout.screen_mybooks) {
    private val binding by viewBinding(ScreenMybooksBinding::bind)
    private val adapter by lazy { MyBookAdapter() }
    private val viewModel:MyBooksVM by viewModels<MyBooksVMImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rec.adapter = adapter
        binding.rec.layoutManager = LinearLayoutManager(requireContext())
        initViewModel()
    }
    private fun initViewModel(){
        viewModel.getBooks().onEach {
            it.onSuccess {
                adapter.submitList(it)
            }
            it.onFailure {
                Toast.makeText(requireContext(),it.message?:"Error!",Toast.LENGTH_SHORT).show()
            }
        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
    }
}