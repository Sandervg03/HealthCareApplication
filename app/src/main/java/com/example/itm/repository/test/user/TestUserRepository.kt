package com.example.itm.repository.test.user

import android.content.Context
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.User
import com.example.itm.repository.interfaces.user.UserRepositoryInterface
import test.data.mapping.UserTestData

class TestUserRepository(
    context: Context
) : UserRepositoryInterface {

    override suspend fun findUser(bsn: Int?, name: String?): Resource<List<User>> {
        val user: User = UserTestData.returnPatient()
        return if (bsn == user.bsn) {
            Resource.Success(listOf(UserTestData.returnPatient()))
        } else {
            Resource.Error(message = "Not Found")
        }
    }

    override suspend fun registerUser(user: User): Resource<User> {
        return Resource.Success(UserTestData.returnPatient())
    }
}