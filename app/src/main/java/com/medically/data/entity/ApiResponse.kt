package com.medically.data.entity

class ApiResponse<T>(
    val exception: Exception? = null,
    val data: T? = null,
)
