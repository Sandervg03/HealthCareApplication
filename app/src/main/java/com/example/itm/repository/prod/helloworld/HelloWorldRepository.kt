package com.example.itm.repository.prod.helloworld

import android.content.Context
import android.util.Log
import com.example.itm.data.api.Api
import com.example.itm.data.api.service.HelloWorld.HelloWorldApiService
import com.example.itm.data.api.util.Resource
import com.example.itm.data.responsemodels.ErrorResponse
import com.example.itm.data.model.HelloWorld
import com.example.itm.repository.interfaces.helloworld.HelloWorldRepositoryInterface
import com.google.gson.Gson
import retrofit2.Response

class HelloWorldRepository(context: Context): HelloWorldRepositoryInterface {

    private val _api = Api.createApi(
        HelloWorldApiService::class.java,
        context
    )

    // Makes an api request, if the request is successful, a Resource.Success instance is returned.
    // If the request is not successful, a Resource.Error is returned and logged in LogCat.
    override suspend fun getHelloWorld(): Resource<HelloWorld> {
        val returnValue: String = try {
            val response: Response<HelloWorld> = _api.getHelloWorld()
            if (response.isSuccessful) {
                response.body()?.message ?: "Successful request but no data returned"
            } else {
                val errorMessage = response.errorBody()?.string()?.let { errorBody ->
                    val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                    errorResponse.message
                } ?: "An unexpected error occurred"
                throw Exception(errorMessage)
            }
        } catch (e: Exception) {
            Log.e("helloWorld", e.message ?: "No exception message available")
            return Resource.Error(message = e.message)
        }
        return Resource.Success(HelloWorld(returnValue))
    }
}