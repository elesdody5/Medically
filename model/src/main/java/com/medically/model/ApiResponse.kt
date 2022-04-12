package com.medically.model

class ApiResponse<T>(
    val exception: Exception? = null,
    val data: T? = null,
)
