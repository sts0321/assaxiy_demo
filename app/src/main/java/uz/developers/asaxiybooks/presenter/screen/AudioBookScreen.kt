package uz.developers.asaxiybooks.presenter.screen

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.nasiyaapp.utils.myLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.developers.asaxiybooks.R
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.databinding.ScreenAudioBookBinding
import java.io.File
import javax.inject.Inject
@AndroidEntryPoint
class AudioBookScreen : Fragment(R.layout.screen_audio_book) {
    private val binding by viewBinding(ScreenAudioBookBinding::bind)
    private var isPlaying = true
    private lateinit var seekBar:SeekBar
    private var audioBook: MediaPlayer? = null
    private val navArgs=navArgs<AudioBookScreenArgs>()
    private val audio by lazy { navArgs.value.audioBook }
    @Inject
    lateinit var shar:Pref

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seekBar = binding.seekBar
        init()
        controllerSound()
    }

    private fun controllerSound() {
        val book = File(shar.getBookLink(bookId = audio.id))
        binding.playBtn.setOnClickListener {
            if (audioBook == null) {
                audioBook = MediaPlayer.create(requireContext(), Uri.fromFile(book))
                audioBook?.setOnCompletionListener {
                    stopPlaying()
                }

                audioBook?.setOnErrorListener { mp, what, extra ->
//                    stopPlaying()
                    true
                }
                audioBook?.setOnPreparedListener {
                    seekBar.max = audioBook!!.duration
                    audioBook!!.start()
                    binding.maxTimeText.text = milliSecondsToTimer(audioBook!!.duration.toLong())
                    isPlaying = true

                    binding.playBtn.setImageResource(R.drawable.pause_1)
                    startSeekBarUpdate()
                }
            } else {
                if (!isPlaying) {
                    pausePlaying()
                } else {
                    resumePlaying()
                }
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // progress -> second
                    audioBook?.seekTo(progress)
                    binding.startTimeText.text = milliSecondsToTimer(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun startSeekBarUpdate() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBar.progress = audioBook!!.currentPosition
                    handler.postDelayed(this, 1000)
                    binding.startTimeText.text = milliSecondsToTimer(audioBook!!.currentPosition.toLong())


                } catch (e: Exception) {
                    e.printStackTrace()
//                    stopPlaying()
                }
            }
        }, 0)
    }

    private fun pausePlaying() {
        audioBook?.pause()
        isPlaying = true
        binding.playBtn.setImageResource(R.drawable.play_button)
    }

    private fun resumePlaying() {
        audioBook?.start()
        isPlaying = false
        binding.playBtn.setImageResource(R.drawable.pause_1)
    }

    private fun stopPlaying() {
        audioBook?.stop()
        audioBook?.release()
        audioBook = null
        isPlaying = true
        binding.playBtn.setImageResource(R.drawable.play_button)
        seekBar.progress = 0
        binding.startTimeText.text = milliSecondsToTimer(0L)
    }
    fun milliSecondsToTimer(milliseconds: Long): String {
        var finalTimerString = ""
        var secondsString = ""

        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
        if (hours > 0) {
            finalTimerString = "$hours:"
        }

        secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            "" + seconds
        }
        finalTimerString = "$finalTimerString$minutes:$secondsString"

        return finalTimerString
    }
    fun init(){
        binding.apply {
            Glide.with(requireContext())
                .load(audio.bookPicture[0])
                .listener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        "${e?.message}".myLog()
                        "${e?.message} error".myLog()
                        bookImage.setImageResource(R.drawable.audio_book)
                       return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .into(bookImage)
            musicName.text=audio.bookName
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        audioBook?.stop()
        audioBook=null
    }
}
