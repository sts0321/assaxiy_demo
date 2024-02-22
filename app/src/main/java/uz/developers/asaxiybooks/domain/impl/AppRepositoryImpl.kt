package uz.developers.asaxiybooks.domain.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(val pref: Pref) : AppRepository {
    private val fireStore = Firebase.firestore
    override fun getAllBooks(): Flow<MyBooksData> = callbackFlow{
        val data=ArrayList<MyBooksData>()
        fireStore.collection("books")
            .get().addOnSuccessListener {
                val size=it.size()
                var index=0
                it.forEach {
                    val name=it.data.getOrDefault("name","")?:""
                    val description=it.data.getOrDefault("description","")?:""
                    val coverImage=it.data.getOrDefault("coverImage", arrayListOf("")) as List<String>
                    val author=it.data.getOrDefault("author", "")?:""
                }
            }
            .addOnFailureListener {

            }
        awaitClose()
    }

}
