package uz.developers.asaxiybooks.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}