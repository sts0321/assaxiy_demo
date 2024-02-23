package uz.developers.asaxiybooks.presenter.screen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.databinding.ScreenSplashBinding
import uz.developers.asaxiybooks.presenter.activity.MainActivity
import uz.developers.asaxiybooks.presenter.viewModel.SplashViewModel
import uz.developers.asaxiybooks.presenter.viewModel.impl.SplashViewModelImpl
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel by viewModels<SplashViewModelImpl>()

    private val binding by viewBinding ( ScreenSplashBinding::bind )





    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.splashTxt.animate().alpha(0f).setDuration(0).start()

        binding.splashTxt.animate()
            .alpha(1f)
            .setDuration(1000).start()



        viewModel.navigateToScreen.observe(viewLifecycleOwner) { bool ->
            if(bool) {
                findNavController().navigate(R.id.action_splashScreen_to_introScreen)
            }else{
                findNavController().navigate(R.id.action_splashScreen_to_loginScreen)
            }
        }


    }
}
