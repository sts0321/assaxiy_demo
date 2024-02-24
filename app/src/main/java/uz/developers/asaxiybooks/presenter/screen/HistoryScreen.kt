package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenHistoryBinding
import uz.developers.asaxiybooks.presenter.adapter.HistoryAdapter

class HistoryScreen : Fragment(R.layout.screen_history) {
    private val binding by viewBinding(ScreenHistoryBinding::bind)
    private var adapter = HistoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.rvHistory.adapter = adapter
    }



}