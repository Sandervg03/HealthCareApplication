package com.example.itm.repository.prod.authorization

import android.content.Context
import android.util.Log
import com.example.itm.data.api.Api
import com.example.itm.data.api.service.Authorization.AuthorizationApiService
import com.example.itm.data.api.util.Resource
import com.example.itm.data.responsemodels.IsAuthResponse
import com.example.itm.data.model.Password
import com.example.itm.repository.interfaces.authorization.AuthorizationRepositoryInterface
import com.example.itm.session.Session
import retrofit2.Response

class AuthorizationRepository(context: Context): AuthorizationRepositoryInterface {
    private val _api: AuthorizationApiService =
        Api.createApi(AuthorizationApiService::class.java, context)

    override suspend fun login(credentials: Password): Resource<IsAuthResponse> {
        val returnValue: IsAuthResponse = try {
            val response: Response<IsAuthResponse> = _api.login(credentials)
            if (response.isSuccessful) {
                Session.user = response.body()?.user
                Session.role = response.body()?.user?.role
                Session.selectedUser = response.body()?.user
                IsAuthResponse(true, response.body()?.user!!, response.code())
            } else {
                throw Exception("Incorrect credentials")
            }
        } catch (e: Exception) {
            Log.e("login", e.message ?: "No exception message available")
            return Resource.Error(message = e.message)
        }
        return Resource.Success(returnValue)
    }

    override suspend fun isAuth(): Boolean {
        val returnValue: Boolean = try {
            val response: Response<IsAuthResponse> = _api.isAuth()
            if (response.isSuccessful) {
                Session.user = response.body()?.user
                Session.role = response.body()?.user?.role
                Session.selectedUser = response.body()?.user
                response.body()?.isAuth ?: true
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e("isAuth", e.message ?: "No exception message available")
            return false
        }
        return returnValue
    }
}