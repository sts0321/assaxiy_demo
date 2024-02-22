package uz.developers.asaxiybooks.presenter.screen

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenProfileBinding

class ProfileScreen: Fragment(R.layout.screen_profile) {
    val binding by viewBinding(ScreenProfileBinding::bind)

}