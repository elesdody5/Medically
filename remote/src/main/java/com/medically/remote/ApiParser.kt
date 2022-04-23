package com.medically.remote

import com.medically.model.ApiResponse
import com.medically.model.Result
import java.lang.Exception

fun <T> ApiResponse<T>.parseApiResponse(): Result<T> {
    data?.let {
        return Result.Success(it)
    }
    return Result.Error(exception ?: Exception())
}