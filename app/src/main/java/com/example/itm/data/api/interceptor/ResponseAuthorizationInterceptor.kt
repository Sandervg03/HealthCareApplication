package com.example.itm.data.api.interceptor

import android.content.Context
import android.util.Log
import com.example.itm.data.sharedpreferences.SharedPreferencesManager
import com.example.itm.util.authorizationevent.UnauthorizedEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

class ResponseAuthorizationInterceptor(context: Context) : Interceptor {

    private val sharedPreferences: SharedPreferencesManager = SharedPreferencesManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Updates the authorization token.
        if (response.isSuccessful) {
            response.headers["Authorization"]?.let {
                sharedPreferences.setSharedPreferenceString(
                    "Authorization",
                    it
                )
            }
        } else if (response.code == 401) {
            Log.d("Unauthorized", "Unauthorized")
            sharedPreferences.removeSharedPreference("Authorization")
            // If the request was not sent to login route, the user will be sent
            // to the login page when the user gets an unauthorized response.
            if (!request.url.toString().contains("/auth/login")) {
                CoroutineScope(Dispatchers.IO).launch {
                    UnauthorizedEvent.triggerUnauthorizedEvent()
                }
            }
        } else {
            Log.d("Unexpected Error", response.message)
        }
        return response
    }

}