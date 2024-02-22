package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenHomeBinding
import uz.developers.asaxiybooks.presenter.adapter.ViewPagerAdapter

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val myBooksScreen=MyBooksScreen()
    private val adapter by lazy { ViewPagerAdapter(this,MyBooksScreen()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.adapter=adapter
    }


    fun initButtons(){

    }
}