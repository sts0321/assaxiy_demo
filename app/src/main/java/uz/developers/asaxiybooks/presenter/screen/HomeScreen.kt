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
    private val libraryScreen=LibraryScreen()
    private val audioBookScreen=AudioBookScreen()
    private val profileScreen=ProfileScreen()
    private val adapter by lazy { ViewPagerAdapter(this,myBooksScreen,libraryScreen,audioBookScreen, profileScreen) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.adapter=adapter
        binding.viewPager.isUserInputEnabled=false
        initButtons()
    }


    fun initButtons(){
       binding.apply {
           bottomNavigationView.setOnItemSelectedListener{
               when(it.itemId){
                   R.id.kitoblarim->viewPager.currentItem=0
                   R.id.kutubhona->viewPager.currentItem=1
                   R.id.audio_kitoblar->viewPager.currentItem=2
                   R.id.profil->viewPager.currentItem=3
               }
               return@setOnItemSelectedListener true
           }
       }
    }
}