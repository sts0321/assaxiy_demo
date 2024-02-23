package uz.developers.asaxiybooks.data.model

data class UserData(
    val name:String,
    val password:String,
    val phone: String,
    val books: ArrayList<String>
)
