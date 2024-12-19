package com.example.itm.repository.interfaces.helloworld

import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.HelloWorld

interface HelloWorldRepositoryInterface {

    suspend fun getHelloWorld(): Resource<HelloWorld>
}