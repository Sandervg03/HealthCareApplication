package com.example.itm.repository.test.helloworld

import android.content.Context
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.HelloWorld
import com.example.itm.data.sharedpreferences.SharedPreferencesManager
import com.example.itm.repository.interfaces.helloworld.HelloWorldRepositoryInterface

class TestHelloWorldRepository(
    context: Context
) : HelloWorldRepositoryInterface {
    private val _sharedPreferencesManager: SharedPreferencesManager = SharedPreferencesManager(context)

    override suspend fun getHelloWorld(): Resource<HelloWorld> {
        if (_sharedPreferencesManager.getSharedPreferenceString("Authorization") == "true") {
            return Resource.Success(HelloWorld("Hello World!"))
        } else {
            return Resource.Error(message = "Unauthorized")
        }
    }
}
