package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nasiyaapp.utils.myLog
import dagger.hilt.android.AndroidEntryPoint
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.data.sourse.PrefImpl
import uz.developers.asaxiybooks.databinding.ScreenPdfreaderBinding
import java.io.File

@AndroidEntryPoint
class ReadBookScreen:Fragment(R.layout.screen_pdfreader) {
    private val binding by viewBinding(ScreenPdfreaderBinding::bind)
    private val navArgs=navArgs<ReadBookScreenArgs>()
    private val bookData by lazy { navArgs.value.book }
    val shar: Pref by lazy {  PrefImpl(requireContext()) }
//    private val name: by lazy { }
//    private val link by lazy { requireArguments().getString("link","").toString() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }
    fun init(){
        binding.bookName.text=bookData.bookName
        val book = File(shar.getBookLink(bookId = bookData.id))
        binding.pdfReader
            .fromFile(book)
            .enableSwipe(true) // allows to block changing pages using swipe
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .onError {
                Toast.makeText(requireContext(),it.message?:"",Toast.LENGTH_SHORT).show()
                "${it.message } ${shar.getBookLink(bookId = bookData.id)}".myLog()
            }
            .load()
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}