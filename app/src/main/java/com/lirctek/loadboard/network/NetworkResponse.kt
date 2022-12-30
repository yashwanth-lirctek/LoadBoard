package com.lirctek.loadboard.network

import okhttp3.Headers


sealed class NetworkResponse<out T : Any> {
    data class Success<T : Any>(
        val body: T,
        val headers: Headers? = null

    ) : NetworkResponse<T>()

    data class Error<T : Any>(
        val error: Throwable,
        var statusCode:Int=200
    ) : NetworkResponse<T>()

}