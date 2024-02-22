package uz.developers.asaxiybooks.domain.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(val pref: Pref) : AppRepository {
    private val fireStore = Firebase.firestore

}
