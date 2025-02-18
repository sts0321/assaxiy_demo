package uz.developers.asaxiybooks.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyBooksData(
    val id:String,
    val bookName:String,
    val bookAuthor:String,
    val bookSize:String,
    val bookPicture:List<String>,
    val descriptions:String,
    val type:String,
    val file:String
):Parcelable