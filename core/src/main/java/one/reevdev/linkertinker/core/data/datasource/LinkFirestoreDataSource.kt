package one.reevdev.linkertinker.core.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import one.reevdev.linkertinker.core.data.Resource
import one.reevdev.linkertinker.core.data.model.LinkCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LinkFirestoreDataSource @Inject constructor() {

    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    fun getAllCategories(): Flow<Resource<List<LinkCategory>>> = callbackFlow {
        trySend(Resource.Loading())
        val docRef = db.collection("categories")
        docRef.get().addOnSuccessListener {
            val data = it.map { doc ->
                doc.toObject(LinkCategory::class.java)
            }
            trySend(Resource.Success(data))
        }.addOnFailureListener {
            trySend(Resource.Failure(it.message.toString(), it))
        }
        awaitClose { channel.close() }
    }

    fun addCategory(category: LinkCategory): Flow<Resource<Boolean>> = callbackFlow {
        trySend(Resource.Loading())
        val docRef = db.collection("categories")
        docRef.document(category.name).set(category)
            .addOnSuccessListener {
                trySend(Resource.Success(true))
            }
            .addOnFailureListener {
                trySend(Resource.Failure(it.localizedMessage.orEmpty()))
            }
        awaitClose { channel.close() }
    }
}