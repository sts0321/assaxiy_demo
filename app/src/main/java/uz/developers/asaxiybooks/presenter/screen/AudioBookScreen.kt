package uz.developers.asaxiybooks.presenter.screen

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenAudioBookBinding

class AudioBookScreen:Fragment(R.layout.screen_audio_book) {
    val binding by viewBinding(ScreenAudioBookBinding::bind)

}