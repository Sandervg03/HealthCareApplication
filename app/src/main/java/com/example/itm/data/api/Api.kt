package com.example.itm.data.api

import android.content.Context
import com.example.itm.data.api.interceptor.RequestAuthorizationInterceptor
import com.example.itm.data.api.interceptor.ResponseAuthorizationInterceptor
import com.example.itm.data.api.interceptor.TimeoutInterceptor
import com.example.itm.util.serializer.DateTypeAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Api {

    companion object {

        // Url to backend api
        private val _apiUrl: String = "http://10.0.2.2:8080"

        private val timeoutMillis = TimeUnit.SECONDS.toMillis(5)

        // Creates an api service based off of the class given.
        // <T> allows us to give different classes by doing:
        // (Class name)::class.java
        fun <T> createApi(
            apiServiceClass: Class<T>,
            context: Context
        ): T {
            val httpClient: OkHttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(RequestAuthorizationInterceptor(context))
                    .addInterceptor(ResponseAuthorizationInterceptor(context))
                    .addInterceptor(TimeoutInterceptor())
                    .connectTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
                    .readTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
                    .writeTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
                    .build()

            val api: T = Retrofit.Builder()
                .baseUrl(_apiUrl)
                .client(httpClient)
                .addConverterFactory(
                    GsonConverterFactory
                        .create(
                            GsonBuilder()
                            .registerTypeAdapterFactory(
                                DateTypeAdapterFactory())
                            .create()
                    )
                )
                .build()
                .create(apiServiceClass)

            return api
        }
    }
}