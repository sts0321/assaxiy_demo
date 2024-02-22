package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nasiyaapp.utils.myLog
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenIntroBinding
import uz.developers.asaxiybooks.presenter.adapter.IntroAdapter

@AndroidEntryPoint
class IntroScreen : Fragment(R.layout.screen_intro) {
    private val adapter by lazy {
        IntroAdapter(requireActivity()) {
            findNavController().navigate(
                IntroScreenDirections.actionIntroScreenToHomeScreen()
            )
        }
    }
    private val binding by viewBinding(ScreenIntroBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "onViewCreated".myLog()
        binding.pager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.pager)
    }
}
