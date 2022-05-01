package com.medically.core

fun Int.toTimeStamp(): Long {
    return this * 1000L
}