package com.example.itm.util.provider

import android.content.Context
import com.example.itm.repository.RepositoryObject
import com.example.itm.repository.interfaces.authorization.AuthorizationRepositoryInterface
import com.example.itm.repository.prod.authorization.AuthorizationRepository
import com.example.itm.repository.test.authorization.TestAuthorizationRepository

object GetOriginalScreen {

    suspend fun get(context: Context): String {
        val authRepository: AuthorizationRepositoryInterface =
            RepositoryProvider.provideMultipleImplementationsRepository(
                context, RepositoryObject.authorization.repositoryInterface
            )

        val isAuth: Boolean = authRepository.isAuth()
        return if (isAuth) {
            "overviewGraph"
        } else {
            "authGraph"
        }
    }

}