package uz.developers.asaxiybooks.data.sourse

interface Pref {
    fun isFirstTime(): Boolean
    fun setFirstTime(isFirstTime: Boolean)

    fun setBookInfo(bookId:String,bookLink:String)
    fun getBookLink(bookId:String):String

    fun setLogIn(boolean: Boolean)

    fun getLogIn():Boolean
}
