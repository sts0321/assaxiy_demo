package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.data.sourse.PrefImpl
import uz.developers.asaxiybooks.databinding.ScreenSplashBinding
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen @Inject constructor(
    val shared : Pref
) : Fragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.splashTxt.animate().alpha(0f).setDuration(0).start()

        binding.splashTxt.animate()
            .alpha(1f)
            .setDuration(1000).start()

        lifecycleScope.launch {
            delay(1500)
            if (!shared.isFirstTime()){
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToHomeScreen())
            }else{
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToIntroScreen())
            }
        }

    }

}