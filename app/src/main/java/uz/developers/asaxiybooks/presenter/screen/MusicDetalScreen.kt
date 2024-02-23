package uz.developers.asaxiybooks.presenter.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.nasiyaapp.utils.myLog
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.data.sourse.PrefImpl
import uz.developers.asaxiybooks.databinding.ScreenMusicDetalBinding
import uz.developers.asaxiybooks.presenter.viewModel.BookDetailViewModel
import uz.developers.asaxiybooks.presenter.viewModel.impl.BookDetailViewModelImpl
import java.io.File
@AndroidEntryPoint
class MusicDetalScreen : Fragment(R.layout.screen_music_detal) {
    private val binding by viewBinding(ScreenMusicDetalBinding::bind)
    private val navArgs=navArgs<MusicDetalScreenArgs>()
    private val audioBookData by lazy { navArgs.value.audioBook }
    private val viewModel: BookDetailViewModel by viewModels<BookDetailViewModelImpl>()
    val shar: Pref by lazy {  PrefImpl(requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInfo()
        if (!shar.getUserInfo().books.contains(audioBookData.id)){
            binding.download.text="Sotib olish"
            binding.download.setOnClickListener {
                binding.progressBar.visibility=View.VISIBLE
                binding.progresBth.visibility=View.VISIBLE
                binding.download.visibility=View.INVISIBLE

                viewModel.setBookInUser(audioBookData.id).onEach {
                    it.onSuccess {
                        if (shar.getBookLink(audioBookData.id)!=""){
                            binding.download.text="Tinglash"
                            binding.download.setOnClickListener {
                                findNavController().navigate(MusicDetalScreenDirections.actionMusicDetalScreenToAudioBookScreen(audioBookData))
                            }
                        }else{
                            initButton()
                        }
                        binding.progresBth.visibility=View.INVISIBLE
                        binding.progresBth.visibility=View.INVISIBLE
                        binding.download.visibility=View.VISIBLE
                    }
                    it.onFailure {
                        binding.progresBth.visibility=View.INVISIBLE
                        binding.progresBth.visibility=View.INVISIBLE
                        binding.download.visibility=View.VISIBLE
                    }
                }.flowWithLifecycle(lifecycle).launchIn(lifecycleScope)
            }
        }else{
            if (shar.getBookLink(audioBookData.id)!=""){
                binding.download.text="Tinglash"
                binding.download.setOnClickListener {
                    findNavController().navigate(MusicDetalScreenDirections.actionMusicDetalScreenToAudioBookScreen(audioBookData))
                }
            }else{
                initButton()
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
        binding.download.text="Yuklash"
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
                            findNavController().navigate(MusicDetalScreenDirections.actionMusicDetalScreenToAudioBookScreen(audioBookData))
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