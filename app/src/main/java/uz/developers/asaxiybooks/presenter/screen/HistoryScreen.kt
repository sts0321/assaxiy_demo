package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenHistoryBinding
import uz.developers.asaxiybooks.presenter.adapter.HistoryAdapter
import uz.developers.asaxiybooks.presenter.viewModel.HistoryVM
import uz.developers.asaxiybooks.presenter.viewModel.impl.HistoryVMImpl

@AndroidEntryPoint
class HistoryScreen : Fragment(R.layout.screen_history) {
    private val binding by viewBinding(ScreenHistoryBinding::bind)
    private var adapter = HistoryAdapter()
    private val viewModel: HistoryVM by viewModels<HistoryVMImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())

        initViewModel()
        initAdapter()
    }

    private fun initViewModel() {
        viewModel.laodPurchaseHistory().onEach {
            it.onSuccess {
                adapter.submitList(it)
            }
            it.onFailure {
                Toast.makeText(requireContext(), it.message ?: "Error!", Toast.LENGTH_SHORT).show()
            }
        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
    }


    private fun initAdapter() {
        adapter.onClick {
            if (it.type == "pdf") {
                findNavController().navigate(
                    HistoryScreenDirections.actionHistoryScreenToBookDetailScreen(
                        it
                    )
                )
            } else {
                findNavController().navigate(
                    HistoryScreenDirections.actionHistoryScreenToMusicDetalScreen(
                        it
                    )
                )
            }
        }

    }

}