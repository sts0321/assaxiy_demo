package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenMybooksBinding
import uz.developers.asaxiybooks.presenter.adapter.MyBookAdapter

@AndroidEntryPoint
class MyBooksScreen:Fragment(R.layout.screen_mybooks) {
    private val binding by viewBinding(ScreenMybooksBinding::bind)
    private val adapter by lazy { MyBookAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rec.adapter = adapter
        binding.rec.layoutManager = LinearLayoutManager(requireContext())
    }
}