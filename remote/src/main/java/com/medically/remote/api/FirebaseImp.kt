package com.medically.remote.api

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.medically.model.*
import com.medically.remote.entities.RemoteSubject
import com.medically.remote.entities.RemoteYear
import com.medically.remote.years
import kotlinx.coroutines.tasks.await

class FirebaseImp constructor(
    private val firebaseFirestore: FirebaseFirestore = Firebase.firestore,
) : NetworkServices {
    private lateinit var yearCollection: CollectionReference
    private lateinit var doctorCollection: CollectionReference

    override suspend fun getAllYears(): ApiResponse<List<Year>> {
        return try {
            val remoteYears = mutableListOf<RemoteYear>()
            years.forEach { year ->
                val collection = firebaseFirestore.collection(year).get().await()
                val subjects = collection.documents.map {
                    RemoteSubject(it.id, it.id)
                }
                remoteYears.add(RemoteYear(year, subjects))
            }
            ApiResponse(data = remoteYears)
        } catch (e: Exception) {
            ApiResponse(e)
        }

    }
}