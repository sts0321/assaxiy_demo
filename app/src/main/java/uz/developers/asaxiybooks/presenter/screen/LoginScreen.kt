package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.widget.textChanges
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.model.LogInData
import uz.developers.asaxiybooks.databinding.ScreenLoginBinding
import uz.developers.asaxiybooks.presenter.viewModel.LoginViewModel
import uz.developers.asaxiybooks.presenter.viewModel.impl.LoginViewModelImpl

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)

    private val viewModel:LoginViewModel by viewModels<LoginViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        combine(

            binding.emailEditText.textChanges().map { it.length>=10 &&it.endsWith("@gmail.com") },
            binding.passwordEditText.textChanges().map { it.length>=6 },
            transform = { number,lastName-> number&& lastName  }
        )
            .onEach { binding.enterBtn.isEnabled = it }
            .flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)



        binding.singUpText.setOnClickListener {

            findNavController().navigate(R.id.action_loginScreen_to_signUpScreen)
        }

        binding.enterBtn.setOnClickListener {

            val gmail = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            viewModel.loginAccount(LogInData(gmail = gmail,password = password) )
        }

        viewModel.messageFlow
            .onEach {
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()

            }
            .launchIn(lifecycleScope)
        viewModel.successFlow
            .onEach {
                findNavController().navigate(R.id.action_loginScreen_to_homeScreen)
            }
            .launchIn(lifecycleScope)
    }
}