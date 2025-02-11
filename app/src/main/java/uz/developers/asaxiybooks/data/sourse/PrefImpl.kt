package uz.developers.asaxiybooks.data.sourse
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.developers.asaxiybooks.data.model.UserData
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.sign

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
        sharedPreferences.edit().putInt("size",user.books.size).apply()
        for (i in 0..<user.books.size){
            sharedPreferences.edit().putString("userBooks$i",user.books[i]).apply()
        }
    }

    override fun getUserInfo():UserData{
        val firstName=sharedPreferences.getString("firstName","Ali")?:""
        val lastName=sharedPreferences.getString("lastName","Ali")?:""
        val gmail=sharedPreferences.getString("gmail","")?:""
        val password=sharedPreferences.getString("password","")?:""
        val id=sharedPreferences.getString("id","")?:""
        val size=sharedPreferences.getInt("size",0)
        val list=ArrayList<String>(size)
        for (i in 0..<size){
            val bookId=sharedPreferences.getString("userBooks$i","")?:""
            if (bookId!=""){
                list.add(bookId)
            }
        }
        return UserData(id, firstName, lastName, password, gmail,list)
    }

    override fun getDownlandBookId(): ArrayList<String> {
        val size=sharedPreferences.getInt("booksIdSize",0)
        val data=ArrayList<String>(size)
        for (i in 0..<size){
            data.add(sharedPreferences.getString("bookId$i","")?:"")
        }
        return data
    }

    override fun setDownlandBookId(bookId: String) {
        val list=getDownlandBookId()
        list.add(bookId)
        sharedPreferences.edit().putInt("booksIdSize",list.size).apply()
        for (i in 0..<list.size){
            sharedPreferences.edit().putString("bookId$i",list[i]).apply()
        }
    }
}



