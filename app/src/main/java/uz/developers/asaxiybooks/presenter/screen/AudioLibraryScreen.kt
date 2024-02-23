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
import com.example.nasiyaapp.utils.myLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenAudioLibraryBinding
import uz.developers.asaxiybooks.presenter.adapter.AudioLibraryScreenAdapter
import uz.developers.asaxiybooks.presenter.viewModel.AudioLibraryVM
import uz.developers.asaxiybooks.presenter.viewModel.impl.AudioLibraryVMImpl
@AndroidEntryPoint
class AudioLibraryScreen:Fragment(R.layout.screen_audio_library) {
    private val binding by viewBinding(ScreenAudioLibraryBinding::bind)
    private val adapter = AudioLibraryScreenAdapter()
    private val viewModel: AudioLibraryVM by viewModels<AudioLibraryVMImpl>()

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
                Toast.makeText(requireContext(),it.message?:"Error!!", Toast.LENGTH_SHORT).show()
            }
        }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)

        adapter.setOnClickBook {
            "click".myLog()
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToMusicDetalScreen(it))
        }
        adapter.setOnClickCategory {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToAllBookScreen(it))
        }
    }
}