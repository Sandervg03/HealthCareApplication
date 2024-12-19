package com.example.itm.repository.interfaces.user

import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.User

interface UserRepositoryInterface {

    suspend fun findUser(bsn: Int?, name: String?): Resource<List<User>>
    suspend fun registerUser(user: User): Resource<User>
}