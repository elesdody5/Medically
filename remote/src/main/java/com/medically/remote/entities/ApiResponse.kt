package com.medically.remote.entities

class ApiResponse<T>(
    val exception: Throwable? = null,
    val data: T? = null,
)
