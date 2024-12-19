package com.example.itm.viewmodel.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.itm.data.api.util.Resource
import com.example.itm.data.responsemodels.IsAuthResponse
import com.example.itm.data.responsemodels.LoginResponse
import com.example.itm.data.model.Password
import com.example.itm.repository.RepositoryObject
import com.example.itm.repository.interfaces.authorization.AuthorizationRepositoryInterface
import com.example.itm.repository.prod.authorization.AuthorizationRepository
import com.example.itm.repository.test.authorization.TestAuthorizationRepository
import com.example.itm.util.provider.RepositoryProvider
import kotlinx.coroutines.launch

class LoginViewModel(context: Context, private val navController: NavHostController): ViewModel(){

    private val _repository: AuthorizationRepositoryInterface =
        RepositoryProvider.provideMultipleImplementationsRepository(
            context, RepositoryObject.authorization.repositoryInterface
        )

    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    private var _loginResponse: MutableLiveData<Resource<LoginResponse>> =
        MutableLiveData(Resource.Empty())

    fun login(credentials: Password) {
        _loginResponse.value = Resource.Fetching()

        // Starts a Coroutine on a different thread to run asynchronously.
        // If there is an error, _loginResponse is set to the Error resource.
        // If not, the user is navigated to the homepage and all previous screens
        // are wiped from the navigation backstack.
        viewModelScope.launch {
            val apiResponse: Resource<IsAuthResponse> = _repository.login(credentials)
            if (apiResponse is Resource.Error) {
                _loginResponse.value = Resource.Error(message = apiResponse.message)
            } else {
                if (apiResponse.data?.code == 200) {
                    navController.navigate("overviewGraph") {
                        popUpTo(0) { inclusive = false }
                    }
                } else {
                    _loginResponse.value = Resource.Error(message = apiResponse.message)
                }
            }
        }
    }
}