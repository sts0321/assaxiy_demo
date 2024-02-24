package uz.developers.asaxiybooks.domain.impl

import com.example.nasiyaapp.utils.myLog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.domain.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(private val pref: Pref) : BooksRepository {
    val fireStore = Firebase.firestore
    override fun getBooksFromUser(): Flow<List<MyBooksData>> = callbackFlow {
        var list: ArrayList<String>
        val data=ArrayList<MyBooksData>()
        fireStore.collection("user")
            .whereEqualTo("gmail", pref.getUserInfo().gmail)
            .get()
            .addOnSuccessListener {
                list = it.documents[0].data?.getOrDefault("books", arrayListOf<String>()) as ArrayList<String>
                val size=list.size
                var index=0
                list.forEach {
                    fireStore.collection("books")
                        .document(it)
                        .get().addOnSuccessListener {
                            index++
                            if (it!=null){
                                val description=(it.data!!.getOrDefault("description","")?:"").toString()
                                val coverImage=it.data!!.getOrDefault("coverImage", arrayListOf("")) as List<String>
                                val author=(it.data!!.getOrDefault("author", "")?:"Ali").toString()
                                val totalSize=(it.data!!.getOrDefault("total size","10 mb")?:"12 mb").toString()
                                val file=(it.data!!.getOrDefault("filePath","")?:"").toString()
                                val name=(it.data!!.getOrDefault("name","")?:"").toString()
                                val type=(it.data!!.getOrDefault("type","mp3")?:"mp3").toString()
                                data.add(
                                    MyBooksData(
                                        id=it.id,
                                        bookName = name,
                                        bookAuthor = author,
                                        bookSize = totalSize,
                                        bookPicture =coverImage,
                                        descriptions =description,
                                        type = type,
                                        file = file
                                    )
                                )
                            }
                            if (size==index){
                                trySend(data)
                            }

                        }
                }
            }
            .addOnFailureListener {

            }
        awaitClose()
    }

    override fun setBookFromUser(bookId: String): Flow<Result<Unit>> = callbackFlow{
        val list=pref.getUserInfo().books
        list.add(bookId)
        var user=pref.getUserInfo()
        user.books=list
        pref.setUserInfo(user)
        fireStore.collection("user")
            .document(pref.getUserInfo().id)
            .update("books",list).addOnSuccessListener {
                trySend(Result.success(Unit))
                channel.close()
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
                channel.close()
            }
        awaitClose()
    }

    override fun getBooksForDownland(): Flow<Result<List<MyBooksData>>> = callbackFlow{
       val list=pref.getDownlandBookId()
       "${list}".myLog()
        if (list.size==0){
            trySend(Result.success(emptyList()))
            channel.close()
        }
        val data=ArrayList<MyBooksData>()
        val size=list.size
        var index=0
        list.forEach {
            fireStore.collection("books")
                .document(it)
                .get().addOnSuccessListener {
                    index++
                    if (it.data!=null){
                        "${it.id}".myLog()
                        val description=(it.data?.getOrDefault("description","")?:"").toString()
                        val coverImage=it.data?.getOrDefault("coverImage", arrayListOf("")) as List<String>
                        val author=(it.data?.getOrDefault("author", "")?:"Ali").toString()
                        val totalSize=(it.data?.getOrDefault("total size","10 mb")?:"12 mb").toString()
                        val file=(it.data?.getOrDefault("filePath","")?:"").toString()
                        val name=(it.data?.getOrDefault("name","")?:"").toString()
                        val type=(it.data?.getOrDefault("type","mp3")?:"mp3").toString()
                        data.add(
                            MyBooksData(
                                id=it.id,
                                bookName = name,
                                bookAuthor = author,
                                bookSize = totalSize,
                                bookPicture =coverImage,
                                descriptions =description,
                                type = type,
                                file = file
                            )
                        )
                    }
                    if (index==size){
                        trySend(Result.success(data))
                        channel.close()
                    }
                }
                .addOnFailureListener {
                    index++
                    trySend(Result.failure(it))
                    channel.close()
                }
        }
        awaitClose()
    }
}
