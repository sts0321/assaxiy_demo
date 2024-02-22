package uz.developers.asaxiybooks.presenter.screen

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenPdfreaderBinding
import java.net.URI

class ReadBookScreen:Fragment(R.layout.screen_pdfreader) {
    private val binding by viewBinding(ScreenPdfreaderBinding::bind)
    private val name by lazy { requireArguments().getString("name","").toString() }
    private val link by lazy { requireArguments().getString("link","").toString() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bookName.text=name
        binding.pdfReader.fromAsset(link)
            .enableSwipe(true) // allows to block changing pages using swipe
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .load();
    }
}