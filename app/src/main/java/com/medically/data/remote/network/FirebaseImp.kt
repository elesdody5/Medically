package com.medically.data.remote.network

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.medically.data.entity.ApiResponse
import com.medically.domain.model.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseImp @Inject constructor(private val firebaseFirestore: FirebaseFirestore) :
    NetworkServices {
    private lateinit var yearCollection: CollectionReference
    private lateinit var doctorCollection: CollectionReference

    override suspend fun getSubjectsByYear(year: String): ApiResponse<List<Subject>> {
        return try {
            yearCollection = firebaseFirestore.collection(year)
            val result = yearCollection.get().await()
            val subjectList = result.documents.map {
                Subject(it.id, it.data?.keys?.toList())
            }
            ApiResponse(data = subjectList)
        } catch (e: Exception) {
            ApiResponse(e)
        }
    }

    override suspend fun getDoctorChapters(
        subject: String,
        doctor: String
    ): ApiResponse<List<Chapter>> {
        return try {
            doctorCollection = yearCollection.document(subject).collection(doctor)

            val chaptersList = doctorCollection.get().await()
                .filter { it.id != "video" && it.id != "pdf" }
                .map {
                    Chapter(it.id, it.getString("image"))
                }

            ApiResponse(data = chaptersList)
        } catch (e: Exception) {
            ApiResponse(e)
        }
    }

    override suspend fun getDoctorPdfs(doctor: String): ApiResponse<List<Pdf>> {
        return try {
            val result = doctorCollection.document("pdf").collection("pdf").get().await()
            val pdfList = result?.documents?.map {
                Pdf(it.getString("name"), it.getString("url"))
            }
            ApiResponse(data = pdfList)
        } catch (e: Exception) {
            ApiResponse(e)
        }
    }

    override suspend fun getDoctorVideo(doctor: String): ApiResponse<Video> {
        return try {

            val result = doctorCollection.document("video").collection("video").get().await()
            val video = result?.documents?.get(0).let {
                Video(it?.getString("name"), it?.getString("url"))
            }

            ApiResponse(data = video)
        } catch (e: Exception) {
            ApiResponse(e)
        }
    }

    override suspend fun getChapterLectures(
        chapter: String
    ): ApiResponse<List<Lecture>> {
        return try {

            val result = doctorCollection.document(chapter).collection("Lectures").get().await()
            val lectureList = result?.documents?.map {
                Lecture(it?.getString("name"), it?.getString("url"))
            }

            ApiResponse(data = lectureList)
        } catch (e: Exception) {
            ApiResponse(e)
        }
    }
}