package uz.developers.asaxiybooks.domain.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.developers.asaxiybooks.data.model.CreateAccount
import uz.developers.asaxiybooks.data.model.LogInData
import uz.developers.asaxiybooks.data.model.MyBooksData
import uz.developers.asaxiybooks.data.model.TypeEnum
import uz.developers.asaxiybooks.data.model.UserData
import uz.developers.asaxiybooks.data.sourse.Pref
import uz.developers.asaxiybooks.domain.AppRepository
import java.lang.ClassCastException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(private val pref: Pref) : AppRepository {
    private val fireStore = Firebase.firestore
    override fun getAllBooks(type: TypeEnum): Flow<Result<List<MyBooksData>>> = callbackFlow{
        val data=ArrayList<MyBooksData>()
        var index=0
        val type1=when(type){
            TypeEnum.PDF-> "pdf"
            TypeEnum.MP3->"mp3"
        }
        fireStore.collection("books")
            .whereEqualTo("type",type1)
            .get().addOnSuccessListener { query->
                val size=query.size()

                query.forEach {
                    index++
                    val name=(it.data.getOrDefault("name","")?:"").toString()

                    if (pref.getBookLink(it.id)!=""){
                        val description=(it.data.getOrDefault("description","")?:"").toString()
                        val coverImage=it.data.getOrDefault("coverImage", arrayListOf("")) as List<String>
                        val author=(it.data.getOrDefault("author", "")?:"Ali").toString()
                        val totalSize=(it.data.getOrDefault("total size","10 mb")?:"12 mb").toString()
                        val file=(it.data.getOrDefault("filePath","")?:"").toString()
                        data.add(
                            MyBooksData(
                                id=it.id,
                                bookName = name,
                                bookAuthor = author,
                                bookSize = totalSize,
                                bookPicture =coverImage,
                                descriptions =description,
                                type = type1,
                                file = file
                            )
                        )
                    }


                    if (index==size){
                        trySend(Result.success(data))
                    }
                }
            }
            .addOnFailureListener {
                index++
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override suspend fun signUpUser(userData: UserData) {
        fireStore.collection("users").add(userData).addOnSuccessListener {

        }
    }

    override fun getCategoryBooks(): Flow<Result<List<Pair<String, String>>>> = callbackFlow{
        val data=ArrayList<Pair<String,String>>()
        fireStore.collection("category")
            .get().addOnSuccessListener {
                val size=it.size()
                var index=0
                it.forEach {
                    index++
                    data.add(Pair(it.id,it.data.getOrDefault("name","Ali").toString()))
                    if (size==index){
                        trySend(Result.success(data))
                    }
                }

            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getBooksInCategory(categoryId: String, type: TypeEnum): Flow<Result<List<MyBooksData>>> = callbackFlow {
        val data=ArrayList<MyBooksData>()
        var index=0
        val type1=when(type){
            TypeEnum.PDF-> "pdf"
            TypeEnum.MP3->"mp3"
        }
        fireStore.collection("books")
            .whereEqualTo("categoryId",categoryId)
            .whereEqualTo("type",type1)
            .get().addOnSuccessListener { query->
                val size=query.size()
                query.forEach {
                    index++
                    val name=(it.data.getOrDefault("name","")?:"").toString()
                    val description=(it.data.getOrDefault("description","")?:"").toString()
                    val coverImage=it.data.getOrDefault("coverImage", arrayListOf("")) as List<String>
                    val author=(it.data.getOrDefault("author", "")?:"Ali").toString()
                    val totalSize=(it.data.getOrDefault("total size","10 mb")?:"12 mb").toString()
                    val file=(it.data.getOrDefault("filePath","")?:"").toString()
                    data.add(
                        MyBooksData(
                            id=it.id,
                            bookName = name,
                            bookAuthor = author,
                            bookSize = totalSize,
                            bookPicture =coverImage,
                            descriptions =description,
                            type = type1,
                            file = file
                        )
                    )
                    if (index==size){

                    }
                }
                trySend(Result.success(data))
            }
            .addOnFailureListener {
                index++
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun logIn(logInData: LogInData): Flow<Result<Unit>> = callbackFlow {
        val gmail = logInData.gmail
       fireStore.collection("user")
           .whereEqualTo("gmail",gmail)
           .whereEqualTo("password",logInData.password)
           .get().addOnSuccessListener {

               it.forEach {
                   val firstName=it.data.getOrDefault("firstName","Ali").toString()
                   val lastName=it.data.getOrDefault("lastName","Ali").toString()
                   val gmail=it.data.getOrDefault("gmail","Ali").toString()
                   val password=it.data.getOrDefault("password","Ali").toString()
                   try{
                       val books=(it.data.getOrDefault("books", listOf(""))?: arrayListOf<String>()) as ArrayList<String>
                       pref.setUserInfo(UserData(it.id, firstName, lastName, password, gmail,books))
                       trySend(Result.success(Unit))
                       channel.close()
                       pref.setLogIn(true)
                   }catch (e:ClassCastException){
                       pref.setUserInfo(UserData(it.id, firstName, lastName, password, gmail,
                           arrayListOf()
                       ))
                       trySend(Result.success(Unit))
                       channel.close()
                       pref.setLogIn(true)
                   }

               }
               trySend(Result.failure(Throwable("Bunaka user yo'q!!")))
               channel.close()
           }
           .addOnFailureListener {
               trySend(Result.failure(it))
               channel.close()
           }

        awaitClose()
    }

    override fun createAccount(createAccount: CreateAccount): Flow<Result<Unit>> = callbackFlow {
       fireStore.collection("user")
           .document(createAccount.id).set(createAccount)
           .addOnSuccessListener{
               trySend(Result.success(Unit))
           }
           .addOnFailureListener {
               trySend(Result.failure(it))
           }

        awaitClose()
    }

}
