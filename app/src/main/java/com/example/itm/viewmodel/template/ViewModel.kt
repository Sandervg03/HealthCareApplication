package com.example.itm.viewmodel.template

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.HelloWorld
import com.example.itm.repository.RepositoryObject
import com.example.itm.repository.interfaces.helloworld.HelloWorldRepositoryInterface
import com.example.itm.repository.prod.helloworld.HelloWorldRepository
import com.example.itm.repository.test.helloworld.TestHelloWorldRepository
import com.example.itm.util.provider.RepositoryProvider
import kotlinx.coroutines.launch

open class ViewModel(context: Context): ViewModel() {

    // Initializes a Repository
    private val repository: HelloWorldRepositoryInterface =
        RepositoryProvider.provideMultipleImplementationsRepository(
            context, RepositoryObject.helloWorld.repositoryInterface
        )

    val helloWorld: LiveData<Resource<HelloWorld>>
        get() = _helloWorld

    // Variable which hold the data of the getHelloWorld function.
    private val _helloWorld: MutableLiveData<Resource<HelloWorld>> =
        MutableLiveData(Resource.Empty())

    // Sets the _helloWorld variable to a Fetching state after which it starts a scope
    // (to run asynchronously) in which it sets the _helloWorld variable to the returned state
    // from the repository.getHelloWorld() function.
    fun getHelloWorld() {
        _helloWorld.value = Resource.Fetching()

        viewModelScope.launch {
            _helloWorld.value = repository.getHelloWorld()
        }
    }

}