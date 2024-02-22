package uz.developers.asaxiybooks.data.model

data class CategoryBooksData(
    val docId:String,
    val name:String,
    val myBooks:List<MyBooksData>
)