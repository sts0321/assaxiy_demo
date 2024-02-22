package uz.developers.asaxiybooks.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryBooksData(
    val docId:String,
    val name:String,
    val myBooks:List<MyBooksData>
):Parcelable