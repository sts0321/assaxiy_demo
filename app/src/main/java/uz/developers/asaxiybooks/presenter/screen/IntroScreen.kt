package uz.developers.asaxiybooks.presenter.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nasiyaapp.utils.myLog
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenIntroBinding
import uz.developers.asaxiybooks.presenter.adapter.IntroAdapter

@AndroidEntryPoint
class IntroScreen : Fragment(R.layout.screen_intro) {
    private val adapter by lazy {
        IntroAdapter(this)
    }
    private val binding by viewBinding(ScreenIntroBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "onViewCreated".myLog()
        binding.pager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.pager)

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.start.setOnClickListener {
                    findNavController().navigate(IntroScreenDirections.actionIntroScreenToLoginScreen())
                }

                if (position == 2) binding.start.text = "start"
                else binding.start.text = "skip"
            }
        })
    }
}
