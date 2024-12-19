package com.example.itm.data.api.service.User

import com.example.itm.data.model.User
import com.example.itm.data.responsemodels.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiService {

    @GET("/medicalprofessional/user")
    suspend fun findUser(
        @Query("bsn")
        bsn: Int?,

        @Query("name")
        name: String?
    ): Response<List<User>>

    @POST("/medicalprofessional/user")
    suspend fun registerUser(
        @Body
        user: User
    ): Response<RegisterResponse>

}