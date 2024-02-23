package uz.developers.asaxiybooks.presenter.screen

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.model.UserData
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.data.sourse.PrefImpl
import uz.developers.asaxiybooks.databinding.ScreenProfileBinding
import uz.developers.asaxiybooks.presenter.viewModel.ProfileVM
import uz.developers.asaxiybooks.presenter.viewModel.impl.ProfileVMImpl
import javax.inject.Inject
@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.screen_profile) {

    @Inject lateinit var pref: Pref

    val binding by viewBinding(ScreenProfileBinding::bind)
    private val viewModel: ProfileVM by viewModels<ProfileVMImpl>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textEditName.text = pref.getUserInfo().firstName + " " + pref.getUserInfo().lastName
        binding.logOut.setOnClickListener{

        }
    }

    private fun dialogLogOut(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setMessage("Log Out")
            .setTitle("Do you want Log Out? ")
            .setPositiveButton("Log Out") { dialog, which ->
                pref.setUserInfo(UserData("","","","",""))
            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Do something else.
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

}