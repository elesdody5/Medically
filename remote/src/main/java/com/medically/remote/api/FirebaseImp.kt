package com.medically.remote.api

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.medically.model.*
import com.medically.remote.entities.RemoteChapter
import com.medically.remote.entities.RemoteDoctor
import com.medically.remote.entities.RemoteSubject
import com.medically.remote.years
import kotlinx.coroutines.tasks.await

object FirebaseImp : NetworkServices {
    private lateinit var yearCollection: CollectionReference
    private lateinit var doctorCollection: CollectionReference
    private lateinit var subjectDoc: DocumentReference

    override suspend fun getAllSubjects(): ApiResponse<List<Subject>> {
        return try {
            val remoteSubjects = mutableListOf<RemoteSubject>()
            years.forEach { year ->
                val collection = Firebase.firestore.collection(year).get().await()
                collection.documents.forEach {
                    remoteSubjects.add(RemoteSubject(it.id, it.id, year, it.getString("image")))
                }
            }
            ApiResponse(data = remoteSubjects)
        } catch (e: Exception) {
            ApiResponse(e)
        }

    }

    override suspend fun getDoctors(year: String, subjectId: String): ApiResponse<List<Doctor>> {
        runCatching {
            yearCollection = Firebase.firestore.collection(year)
            subjectDoc = yearCollection.document(subjectId)
            val doctors = subjectDoc.get().await().data?.keys?.mapNotNull {
                if (it != "image") {
                    RemoteDoctor(subjectId, it)
                } else {
                    null
                }
            }
            return ApiResponse(data = doctors)
        }.onFailure {
            return ApiResponse(it)
        }

        return ApiResponse(Exception())
    }

    override suspend fun getChapters(doctorName: String): ApiResponse<List<Chapter>> {
        val doctorCollection = subjectDoc.collection(doctorName)
        runCatching {
            val chaptersList = doctorCollection.get().await()
                .filter { it.id != "video" && it.id != "pdf" }
                .map {
                    RemoteChapter(it.id, it.id, doctorName, it.getString("image"))
                }
            return ApiResponse(data = chaptersList)
        }.onFailure {
            return ApiResponse(it)
        }
        return ApiResponse(Exception("Unknown"))
    }

}