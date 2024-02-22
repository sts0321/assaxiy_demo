package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
import uz.developers.asaxiybooks.databinding.ScreenDetailBinding
import uz.developers.asaxiybooks.presenter.viewModel.BookDetailViewModel
import uz.developers.asaxiybooks.presenter.viewModel.impl.BookDetailViewModelImpl
import java.io.File
import javax.inject.Inject

class BookDetailScreen : Fragment(R.layout.screen_detail) {
    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: BookDetailViewModel by viewModels<BookDetailViewModelImpl>()
    private val navArgs=navArgs<ReadBookScreenArgs>()
    private val bookData by lazy { navArgs.value.book }
    val shar: Pref by lazy {  PrefImpl(requireContext())}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initInfo()
        initButton()
    }
    fun initInfo(){
        Glide.with(binding.root.context)
            .load(bookData.bookPicture[0])
            .into(binding.img)
        binding.name.text=bookData.bookName
        binding.autor.text=bookData.bookAuthor
        binding.description.text=bookData.descriptions
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.seekBar.visibility=View.GONE
        binding.progres.visibility=View.GONE
    }
    fun initButton(){
        binding.download.setOnClickListener {
            binding.seekBar.visibility=View.VISIBLE
            binding.progres.visibility=View.VISIBLE
            binding.download.visibility=View.GONE
            val book = File.createTempFile(bookData.bookName, "pdf")
            Firebase.storage.getReferenceFromUrl(bookData.file)
                .getFile(book)
                .addOnSuccessListener {
                    shar.setBookInfo(bookId = bookData.id, bookLink = bookData.file)
                    Log.d("TTT", "OnSuccess")

                    binding.apply {
                        seekBar.visibility=View.GONE
                        progres.visibility=View.GONE
                        binding.download.visibility=View.VISIBLE
                        binding.download.setOnClickListener {

                        }
                    }
                }
                .addOnFailureListener{
                    "${it.message}".myLog()
                    binding.apply {
                        seekBar.visibility=View.GONE
                        progres.visibility=View.GONE
                        binding.download.visibility=View.VISIBLE
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