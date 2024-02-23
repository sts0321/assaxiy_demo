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
import uz.developers.asaxiybooks.data.model.CreateAccount
import uz.developers.asaxiybooks.databinding.ScreenSingupBinding
import uz.developers.asaxiybooks.presenter.viewModel.SignInViewModel
import uz.developers.asaxiybooks.presenter.viewModel.impl.SignInViewModelImpl

@AndroidEntryPoint
class SignUpScreen : Fragment(R.layout.screen_singup){

    private val binding by viewBinding(ScreenSingupBinding::bind)

    private val viewModel:SignInViewModel by viewModels<SignInViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        combine(

            binding.firstNameEditText.textChanges().map { it.length>=3 },
            binding.lastNameEditText.textChanges().map { it.length>=3 },
            binding.editGmailText.textChanges().map { it.length>=10 &&it.endsWith("@gmail.com") },
            binding.editPasswordText.textChanges().map { it.length>=6 },
            transform = { firstName,lastName,gmail,password-> firstName && lastName && gmail && password  }
        )
            .onEach { binding.saveBtn.isEnabled = it }
            .flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)

        binding.logText.setOnClickListener {

            findNavController().navigate(R.id.action_signUpScreen_to_loginScreen)
        }


        binding.saveBtn.setOnClickListener {
            val firstName =  binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val gmail = binding.editGmailText.text.toString()
            val password = binding.editPasswordText.text.toString()
            viewModel.createAccount(CreateAccount( firstName = firstName,lastName = lastName,gmail = gmail, password = password))
        }

        viewModel.messageFlow
            .onEach {
                Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()

            }
            .launchIn(lifecycleScope)

        viewModel.successFlow
            .onEach {
                findNavController().navigate(R.id.action_signUpScreen_to_homeScreen)
            }
            .launchIn(lifecycleScope)

    }

}