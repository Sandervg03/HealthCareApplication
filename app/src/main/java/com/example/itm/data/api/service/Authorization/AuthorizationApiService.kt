package com.example.itm.data.api.service.Authorization

import com.example.itm.data.responsemodels.IsAuthResponse
import com.example.itm.data.model.Password
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthorizationApiService {

    @POST("/auth/login")
    suspend fun login(@Body credentials: Password): Response<IsAuthResponse>

    @GET("/auth/isAuth")
    suspend fun isAuth(): Response<IsAuthResponse>
}