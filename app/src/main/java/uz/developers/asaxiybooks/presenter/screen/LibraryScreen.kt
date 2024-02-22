package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenLibraryBinding

class LibraryScreen:Fragment(R.layout.screen_library) {

    val binding by viewBinding(ScreenLibraryBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}