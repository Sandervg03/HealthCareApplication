package com.example.itm.viewmodel.user

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.User
import com.example.itm.data.model.validateRegister
import com.example.itm.repository.RepositoryObject
import com.example.itm.repository.interfaces.user.UserRepositoryInterface
import com.example.itm.repository.prod.user.UserRepository
import com.example.itm.repository.test.user.TestUserRepository
import com.example.itm.ui.screens.Screens
import com.example.itm.util.provider.RepositoryProvider
import kotlinx.coroutines.launch


class UserViewModel(
    context: Context,
    private val navController: NavHostController
): ViewModel() {

    private val _repository: UserRepositoryInterface =
        RepositoryProvider.provideMultipleImplementationsRepository(
            context, RepositoryObject.user.repositoryInterface
        )

    private val _findUserResult: MutableLiveData<Resource<List<User>>> =
        MutableLiveData(Resource.Empty())

    val findUserResult: LiveData<Resource<List<User>>>
        get() = _findUserResult

    // If it is possible to parse input to an integer, bsn is set to the input as integer.
    // If this is not the case, name is set to the input as string.
    fun findUser(input: String) {
        _findUserResult.value = Resource.Fetching()

        val bsn: Int? = if (input.toIntOrNull() != null) {
            input.toInt()
        } else {
            null
        }

        val name: String? = if (input.toIntOrNull() == null) {
            input
        } else {
            null
        }

        viewModelScope.launch {
            _findUserResult.value = _repository.findUser(bsn, name)
        }
    }

    private val _registerUserResult: MutableLiveData<Resource<User>> =
        MutableLiveData(Resource.Empty())

    val registerUserResult: LiveData<Resource<User>>
        get() = _registerUserResult

    fun registerUser(user: User) {
        try {
            _registerUserResult.value = Resource.Fetching()

            user.validateRegister()

            viewModelScope.launch {
                val apiResponse = _repository.registerUser(user)
                if (apiResponse is Resource.Error) {
                    _registerUserResult.value = apiResponse
                } else {
                    navController.navigate(Screens.PatientSearchScreen.route) {
                        popUpTo(0)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("userRegister", e.message ?: "No exception message found")
            _registerUserResult.value =
                Resource.Error(message = "Not all fields are filled in.")
        }
    }
}