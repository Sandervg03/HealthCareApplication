package com.example.itm.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import android.content.Context
import com.example.itm.data.sharedpreferences.SharedPreferencesManager

class RequestAuthorizationInterceptor(context: Context) : Interceptor {

    private val sharedPreferences: SharedPreferencesManager = SharedPreferencesManager(context)

    // Add the authorization token to the Authorization header.
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!request.url.toString().contains("login")) {
            val jwtToken = sharedPreferences.getSharedPreferenceString("Authorization")
            jwtToken?.let {
                val newRequest = request.newBuilder()
                    .addHeader("Authorization", it)
                    .build()
                return chain.proceed(newRequest)
            }
        }
        return chain.proceed(request)
    }
}