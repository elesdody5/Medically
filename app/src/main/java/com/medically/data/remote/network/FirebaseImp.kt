package com.medically.data.remote.network

import com.google.firebase.firestore.FirebaseFirestore
import com.medically.data.entity.ApiResponse
import com.medically.data.entity.Subject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseImp @Inject constructor(private val firebaseFirestore: FirebaseFirestore) :
    NetworkServices {
    override suspend fun getSubjectsByYear(year: String): ApiResponse<List<Subject>> {
        return try {
            val result = firebaseFirestore.collection(year).get().await()
            val subjectList = result.documents.map {
                Subject(it.id, it.data?.keys?.toList())
            }
            ApiResponse(data = subjectList)
        } catch (e: Exception) {
            ApiResponse(e)
        }
    }
}