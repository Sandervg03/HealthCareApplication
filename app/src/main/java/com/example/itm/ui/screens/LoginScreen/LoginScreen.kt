package com.example.itm.ui.screens.LoginScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itm.R
import com.example.itm.data.api.util.Resource
import com.example.itm.data.responsemodels.LoginResponse
import com.example.itm.data.model.Password
import com.example.itm.ui.composables.Errors.ShakeEffect
import com.example.itm.ui.screens.Screens
import com.example.itm.viewmodel.login.LoginViewModel

class LoginScreen(
    private val context: Context,
    private val navController: NavHostController,
    private val viewModel: LoginViewModel = LoginViewModel(context, navController)
) {
    
    @Composable
    fun Create() {
        ScreenContentProvider()
    }

    // If true, disable login button interaction
    private var isPending: Boolean = false

    @Composable
    private fun ScreenContentProvider() {

        val loginResponse: Resource<LoginResponse>? by viewModel.loginResponse.observeAsState()

        val gradient = Brush.verticalGradient(
            colors = listOf(Color(0xFF78C0A8), Color(0xFFA8E063))
        )

        var error: String by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradient),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (loginResponse) {
                is Resource.Error -> {
                    isPending = false
                    error = (loginResponse as Resource.Error).message.toString()
                }
                is Resource.Fetching -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> { }
                else -> {
                    isPending = false
                }
            }
            ScreenContent(navController, error)
        }
    }

    @Composable
    private fun ScreenContent(navController: NavHostController, error: String) {
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "ITM"
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        OutlinedTextField(
            value = username,
            placeholder = {
                Text(
                    text = "Username",
                    fontFamily = FontFamily(Font(R.font.robotocondensed_regular))
                )
                          },
            onValueChange = { username = it },
            modifier = Modifier.testTag("username")
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(
                    text = "Password",
                    fontFamily = FontFamily(Font(R.font.robotocondensed_regular))
                )
                          },
            modifier = Modifier.testTag("password")
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(40.dp),
            shape = RoundedCornerShape(50),
            onClick = {
                isPending = true
                viewModel.login(Password(username, password))
                      },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB3D9F1)),
            enabled = !isPending
        ) {
            Text(
                text = "Log in",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.robotocondensed_regular))
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Text(
            modifier = Modifier
                .clickable {
                    navController.navigate(Screens.ForgotPasswordScreen.route)
                },
            text = "Forgot password?",
            fontSize = 15.sp,
            textDecoration = TextDecoration.Underline,
            fontFamily = FontFamily(Font(R.font.robotocondensed_regular))
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        if (error != "") {
            ShakeEffect {
                Text(
                    text = error,
                    color = Color.Red,
                    fontFamily = FontFamily(Font(R.font.robotocondensed_regular))
                )
            }
        }
    }
}