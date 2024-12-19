package com.example.itm.repository

import com.example.itm.util.provider.RepositoryProvider

fun registerRepositories() {
    val repositories = RepositoryObject::class.sealedSubclasses.mapNotNull { it.objectInstance }
    repositories.forEach { repo -> RepositoryProvider.registerRepository(repo) }
}