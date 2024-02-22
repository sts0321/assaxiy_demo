package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.introhomework.R
import com.example.introhomework.databinding.Page2Binding

class IntroPages : Fragment() {
    private lateinit var binding: Page2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = Page2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pos = requireArguments().getInt("POS", 0)

        when (pos) {
            0 -> {
                binding.image.setImageResource(R.drawable.task2_1)
                binding.textTitle.text = "100% SAFETY GUARANTEE"
                binding.textDescription.text = "all your money wit be treated in a safe way."
            }

            1 -> {
                binding.image.setImageResource(R.drawable.task2_2)
                binding.textTitle.text = "HIGH RETRIBUTION"
                binding.textDescription.text = "our product ensure wor your nvostnori will\nget a high rate of return"
            }
            else -> {
                binding.image.setImageResource(R.drawable.task2_3)
                binding.textTitle.text = "EXPENSE AT ANY TIME"
                binding.textDescription.text = "whenever you need money, you can free\nfeel to take it at any time"
            }
        }
    }


}