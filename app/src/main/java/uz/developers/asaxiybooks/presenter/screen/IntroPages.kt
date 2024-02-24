package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nasiyaapp.utils.myLog
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.PageIntroBinding

@AndroidEntryPoint
class IntroPages() : Fragment(R.layout.page_intro) {
    private val binding by viewBinding(PageIntroBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pos = requireArguments().getInt("POS", 0)

        when (pos) {
            0 -> {

                binding.image.setImageResource(R.drawable.intro1)
            }
            1 -> {

                binding.image.setImageResource(R.drawable.intro2)
            }
            else -> {

                binding.image.setImageResource(R.drawable.intro3)
            }
        }
    }
}