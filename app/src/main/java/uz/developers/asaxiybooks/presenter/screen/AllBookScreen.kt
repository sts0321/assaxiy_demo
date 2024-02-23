package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenAudioBookBinding

class AllBookScreen:Fragment(R.layout.screen_allbooks) {

    private val binding by viewBinding(ScreenAudioBookBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}