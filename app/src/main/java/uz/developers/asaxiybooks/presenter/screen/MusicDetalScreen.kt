package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.nasiyaapp.utils.myLog
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.data.sourse.PrefImpl
import uz.developers.asaxiybooks.databinding.ScreenMusicDetalBinding
import java.io.File

class MusicDetalScreen : Fragment(R.layout.screen_music_detal) {
    private val binding by viewBinding(ScreenMusicDetalBinding::bind)
    private val navArgs=navArgs<ReadBookScreenArgs>()
    private val audioBookData by lazy { navArgs.value.book }
    val shar: Pref by lazy {  PrefImpl(requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInfo()
        initButton()
        if (shar.getBookLink(audioBookData.id)!=""){
            binding.download.text="Tinglash"
            binding.download.setOnClickListener {
                findNavController().navigate(BookDetailScreenDirections.actionBookDetailScreenToReadBookScreen(audioBookData))
            }
        }
    }
    fun initInfo(){
        Glide.with(binding.root.context)
            .load(audioBookData.bookPicture[0])
            .into(binding.img)
        binding.name.text=audioBookData.bookName
        binding.autor.text=audioBookData.bookAuthor
        binding.description.text=audioBookData.descriptions
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.seekBar.visibility= View.GONE
        binding.progres.visibility= View.GONE
    }
    fun initButton(){
        binding.download.setOnClickListener {
            binding.seekBar.visibility= View.VISIBLE
            binding.progres.visibility= View.VISIBLE
            binding.download.visibility= View.INVISIBLE
            val book = File.createTempFile(audioBookData.bookName, ".${audioBookData.type}")
            Firebase.storage.getReferenceFromUrl(audioBookData.file)
                .getFile(book)
                .addOnSuccessListener {
                    book.parent.myLog()
                    shar.setBookInfo(bookId = audioBookData.id, bookLink = "${book.parent}/${book.name}")
                    Log.d("TTT", "OnSuccess")

                    binding.apply {
                        seekBar.visibility= View.GONE
                        progres.visibility= View.GONE
                        binding.download.visibility= View.VISIBLE
                        binding.download.text="Tinglash"
                        binding.download.setOnClickListener {
                            findNavController().navigate(BookDetailScreenDirections.actionBookDetailScreenToReadBookScreen(audioBookData))
                        }
                    }
                }
                .addOnFailureListener{
                    "${it.message}".myLog()
                    binding.apply {
                        seekBar.visibility= View.GONE
                        progres.visibility= View.GONE
                        binding.download.visibility= View.VISIBLE
                    }
                }
                .addOnProgressListener {
                    val prot=it.bytesTransferred*100/it.totalByteCount
                    binding.progres.text="$prot%"
                    binding.seekBar.setProgress(prot.toInt())
                }
        }
    }
}