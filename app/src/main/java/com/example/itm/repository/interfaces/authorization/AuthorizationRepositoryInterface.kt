package com.example.itm.repository.interfaces.authorization

import com.example.itm.data.api.util.Resource
import com.example.itm.data.responsemodels.IsAuthResponse
import com.example.itm.data.model.Password

interface AuthorizationRepositoryInterface {

    suspend fun login(credentials: Password): Resource<IsAuthResponse>
    suspend fun isAuth(): Boolean
}