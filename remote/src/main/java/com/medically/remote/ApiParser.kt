package com.medically.remote

import com.medically.model.Result
import com.medically.remote.entities.ApiResponse

fun <T> ApiResponse<T>.parseApiResponse(): Result<T> {
    data?.let {
        return Result.Success(it)
    }
    return Result.Error(exception ?: Exception())
}