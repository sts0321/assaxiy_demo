package uz.developers.asaxiybooks.data.sourse

import uz.developers.asaxiybooks.data.model.UserData

interface Pref {
    fun isFirstTime(): Boolean
    fun setFirstTime(isFirstTime: Boolean)

    fun setBookInfo(bookId:String,bookLink:String)
    fun getBookLink(bookId:String):String

    fun setLogIn(boolean: Boolean)

    fun getLogIn():Boolean
    fun setUserInfo(user: UserData)
    fun getUserInfo():UserData
    fun getDownlandBookId():List<String>
    fun setDownlandBookId(bookId:String)
}
