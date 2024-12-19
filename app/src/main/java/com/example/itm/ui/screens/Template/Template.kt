package com.example.itm.ui.screens.Template

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.HelloWorld
import com.example.itm.data.sharedpreferences.SharedPreferencesManager
import com.example.itm.ui.screens.Screens
import com.example.itm.viewmodel.template.ViewModel

class Template(
    private val context: Context,
    private val viewModel: ViewModel = ViewModel(context)
) {

    private var isPending: Boolean = false

    @Composable
    fun Create(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag("HomePage"),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Observes the helloWorld variable in the viewmodel.
            val helloWorldResponse: Resource<HelloWorld>? by viewModel.helloWorld.observeAsState()
            // (Switch statement) Checks what type of response Resource the helloWorld variable is
            // and displays a text message accordingly.
            when(helloWorldResponse) {
                is Resource.Empty -> {
                    isPending = false
                    Text(text = "Nothing to see")
                }
                is Resource.Error -> {
                    isPending = false
                    Text(
                        text = (helloWorldResponse as Resource.Error<HelloWorld>).message!!
                    )
                }
                is Resource.Fetching -> {
                    Text(
                        text = "Loading..."
                    )
                }
                is Resource.Success -> {
                    isPending = false
                    Text(
                        text = (helloWorldResponse as Resource.Success<HelloWorld>).data!!.message
                    )
                }
                else -> {
                    isPending = false
                    Text(text = "Something went wrong...")
                }
            }

            // Button to make an api request.
            Button(
                onClick = {
                    isPending = true
                    viewModel.getHelloWorld()
                          },
                enabled = !isPending
            ) {
                Text(text = "Get hello world!")
            }

            Button(onClick = {
                SharedPreferencesManager(context).removeSharedPreference("Authorization")
                }
            ) {
                Text(text = "Clear Authorization token")
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = {
                    navController.navigate(Screens.LoginScreen.route)  {
                        popUpTo(0) { inclusive = false }
                    }
                }
            ) {
                Text(text = "Go To LoginScreen")
            }
            
            Text(text = "HomePage")
        }
    }
}