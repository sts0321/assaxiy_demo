package uz.developers.asaxiybooks.presenter.screen

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nasiyaapp.utils.myLog
import com.github.barteksc.pdfviewer.source.DocumentSource
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.databinding.ScreenPdfreaderBinding
import java.io.File
import java.net.URI

class ReadBookScreen:Fragment(R.layout.screen_pdfreader) {
    private val binding by viewBinding(ScreenPdfreaderBinding::bind)
    private val navArgs=navArgs<ReadBookScreenArgs>()
//    private val name: by lazy { }
//    private val link by lazy { requireArguments().getString("link","").toString() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()


    }

    fun init(){
        binding.bookName.text=navArgs.value.name
        val book = File.createTempFile("Book", "pdf")
        Firebase.storage.getReferenceFromUrl(navArgs.value.link)
            .getFile(book)
            .addOnSuccessListener {
                Log.d("TTT", "OnSuccess")
                binding.pdfReader
                    .fromFile(book)
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .load()
                binding.apply {
                    seekBar.visibility=View.GONE
                    progres.visibility=View.GONE
                }
            }
            .addOnFailureListener{
                "${it.message}".myLog()
                binding.apply {
                    seekBar.visibility=View.GONE
                    progres.visibility=View.GONE
                }
            }
            .addOnProgressListener {
                val prot=it.bytesTransferred*100/it.totalByteCount
                binding.progres.text="$prot%"
                binding.seekBar.setProgress(prot.toInt())
            }
    }

}