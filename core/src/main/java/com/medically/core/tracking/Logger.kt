package com.medically.core.tracking

private const val DEFAULT_TAG = "MedicallyApp"

interface Logger {
    fun logInfo(tag: String = DEFAULT_TAG, message: String?): Unit = println("$tag: $message")
    fun logError(tag: String = DEFAULT_TAG, message: String?): Unit = println("$tag: $message")
    fun logException(throwable: Throwable): Unit = throwable.printStackTrace()
}
