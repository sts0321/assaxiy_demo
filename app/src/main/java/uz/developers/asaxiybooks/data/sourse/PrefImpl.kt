package uz.developers.asaxiybooks.data.sourse
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class PrefImpl @Inject constructor(
    @ApplicationContext context: Context
): Pref {
    private  var sharedPreferences:SharedPreferences
    init {
        sharedPreferences=context.getSharedPreferences("ChessGame",Context.MODE_PRIVATE)
    }

    override fun isFirstTime(): Boolean {
        return sharedPreferences.getBoolean("FIRST_TIME",false)
    }

    override fun setFirstTime(isFirstTime: Boolean) {
        sharedPreferences.edit().putBoolean("FIRST_TIME",isFirstTime).apply()
    }
}



