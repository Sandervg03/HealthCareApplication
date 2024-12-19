package com.example.itm.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

class TimeoutInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(chain.request())
        } catch (e: SocketTimeoutException) {
            throw TimeoutException("Request timed out, please try again later.")
        } catch (e: IOException) {
            throw NetworkException("Failed to connect, please confirm your internet connection.")
        }
    }
}

class TimeoutException(message: String) : IOException(message)
class NetworkException(message: String) : IOException(message)
