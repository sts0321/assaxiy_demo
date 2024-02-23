package uz.developers.asaxiybooks.data.sourse
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.developers.asaxiybooks.data.model.UserData
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
        return sharedPreferences.getBoolean("FIRST_TIME",true)
    }

    override fun setFirstTime(isFirstTime: Boolean) {
        sharedPreferences.edit().putBoolean("FIRST_TIME",isFirstTime).apply()
    }

    override fun setBookInfo(bookId: String, bookLink: String) {
        sharedPreferences.edit().putString(bookId,bookLink).apply()
    }

    override fun getBookLink(bookId: String):String=sharedPreferences.getString(bookId,"")?:""

    override fun setLogIn(boolean: Boolean) {
        sharedPreferences.edit().putBoolean("LOG",boolean).apply()
    }

    override fun getLogIn(): Boolean {
       return sharedPreferences.getBoolean("LOG",false)
    }

    override fun setUserInfo(user:UserData){
        sharedPreferences.edit().putString("firstName",user.firstName).apply()
        sharedPreferences.edit().putString("id",user.id).apply()
        sharedPreferences.edit().putString("lastName",user.lastName).apply()
        sharedPreferences.edit().putString("gmail",user.gmail).apply()
        sharedPreferences.edit().putString("password",user.password).apply()
    }

    override fun getUserInfo():UserData{
        val firstName=sharedPreferences.getString("firstName","Ali")?:""
        val lastName=sharedPreferences.getString("lastName","Ali")?:""
        val gmail=sharedPreferences.getString("gmail","")?:""
        val password=sharedPreferences.getString("password","")?:""
        val id=sharedPreferences.getString("id","")?:""
        return UserData(id, firstName, lastName, password, gmail)
    }
}



