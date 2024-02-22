package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.presenter.viewModel.SplashViewModel
import uz.developers.asaxiybooks.presenter.viewModel.impl.SplashViewModelImpl
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToScreen.observe(viewLifecycleOwner) { bool ->
            if(bool) {
                findNavController().navigate(R.id.action_splashScreen_to_introScreen)
            }else{
                findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
            }
        }
        //gjfodplfgnhjgfopsgkhfopdaofgijfodpsofgkhfopd[sfgokhfodp
    }
}
