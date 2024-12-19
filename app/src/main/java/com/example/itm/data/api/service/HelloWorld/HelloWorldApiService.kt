package com.example.itm.data.api.service.HelloWorld

import com.example.itm.data.model.HelloWorld
import retrofit2.Response
import retrofit2.http.GET

interface HelloWorldApiService {

    @GET("/patient")
    suspend fun getHelloWorld(): Response<HelloWorld>
}