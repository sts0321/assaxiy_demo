package uz.developers.asaxiybooks.domain.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.domain.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(private val pref: Pref) : BooksRepository {
    val fireStore = Firebase.firestore
    override fun getBooks(): Flow<ArrayList<MyBooksData>> = callbackFlow {
        var list = arrayListOf<String>()
        fireStore.collection("user").whereEqualTo("gmail", pref.getUserInfo().gmail).get()
            .addOnSuccessListener {
                list = it.documents[0].data?.getOrDefault("books", arrayListOf<String>()) as ArrayList<String>

            }
    }
}
