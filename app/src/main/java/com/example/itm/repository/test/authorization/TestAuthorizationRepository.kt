package com.example.itm.repository.test.authorization

import android.content.Context
import com.example.itm.data.api.util.Resource
import com.example.itm.data.responsemodels.IsAuthResponse
import com.example.itm.data.model.Password
import com.example.itm.data.sharedpreferences.SharedPreferencesManager
import com.example.itm.repository.interfaces.authorization.AuthorizationRepositoryInterface

class TestAuthorizationRepository(
    context: Context
) : AuthorizationRepositoryInterface {
    private val _sharedPreferencesManager: SharedPreferencesManager = SharedPreferencesManager(context)
    private val _correctCredentials: Password = Password("johndoe", "Test.123")

    override suspend fun login(credentials: Password): Resource<IsAuthResponse> {
        if (credentials == _correctCredentials) {
            return Resource.Success(IsAuthResponse(true, null, 200))
        } else {
            return Resource.Error(message = "Incorrect credentials")
        }
    }

    override suspend fun isAuth(): Boolean {
        if (_sharedPreferencesManager.getSharedPreferenceString("Authorization") == "true") {
            return true
        } else {
            return false
        }
    }
}
