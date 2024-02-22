package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenIntroBinding
import uz.developers.asaxiybooks.presenter.adapter.IntroAdapter

@AndroidEntryPoint
class IntroScreen: Fragment(R.layout.screen_intro) {
    private val adapter by lazy { IntroAdapter(requireActivity()) }
    private val binding by viewBinding(ScreenIntroBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.pager)
    }
}