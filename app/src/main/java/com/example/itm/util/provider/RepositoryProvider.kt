package com.example.itm.util.provider

import android.content.Context
import com.example.itm.repository.RepositoryObject

object RepositoryProvider {

    private val _repositoryMapping: MutableMap<Class<*>, Pair<Class<*>, Class<*>>> = mutableMapOf()

    fun <T> registerRepository(repository: RepositoryObject<T>) {
        _repositoryMapping[repository.repositoryInterface] =
            repository.testRepository to repository.prodRepository
    }

    fun <T> provideMultipleImplementationsRepository(
        context: Context,
        key: Class<T>
    ): T {
        val (testRepository, prodRepository) = _repositoryMapping[key] ?:
        throw IllegalArgumentException("No repository registered for type ${key.name}")
        val repository = if (System.getProperty("TEST_MODE") == "true") {
            testRepository
        } else {
            prodRepository
        }
        return (repository.getConstructor(Context::class.java).newInstance(context) as T)
    }

}