package uz.developers.asaxiybooks.data.model


data class UserOnlineData(
    val firstName:String,
    val lastName:String,
    val password:String,
    val gmail: String,
    val books:ArrayList<String>
)
