package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.developers.asaxiybooks.R

class IntroScreen: Fragment(R.layout.screen_intro) {
    private val adapter by lazy { IntroAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}