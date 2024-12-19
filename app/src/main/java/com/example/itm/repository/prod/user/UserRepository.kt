package com.example.itm.repository.prod.user

import android.content.Context
import android.util.Log
import com.example.itm.data.api.Api
import com.example.itm.data.api.service.User.UserApiService
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.User
import com.example.itm.data.responsemodels.ErrorResponse
import com.example.itm.data.responsemodels.RegisterResponse
import com.example.itm.repository.interfaces.user.UserRepositoryInterface
import com.google.gson.Gson
import retrofit2.Response

class UserRepository(context: Context): UserRepositoryInterface {

    private val _api: UserApiService =
        Api.createApi(
            UserApiService::class.java,
            context
        )

    override suspend fun findUser(bsn: Int?, name: String?): Resource<List<User>> {
        val returnValue: List<User> = try {
            val response: Response<List<User>> = _api.findUser(bsn, name)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            Log.e("findUser", e.message ?: "No exception message found.")
            return Resource.Error(message = e.message)
        }
        return Resource.Success(returnValue)
    }

    override suspend fun registerUser(user: User): Resource<User> {
        val returnValue: User = try {
            val response: Response<RegisterResponse> = _api.registerUser(user)
            if (response.isSuccessful) {
                user
            } else {
                val errorMessage = response.errorBody()?.string()?.let { errorBody ->
                    val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                    errorResponse.message
                } ?: "An unexpected error occurred"
                throw Exception(errorMessage)
            }
        } catch (e: Exception) {
            Log.e("registerUser", e.message ?: "No exception message found.")
            return Resource.Error(message = e.message)
        }
        return Resource.Success(returnValue)
    }
}