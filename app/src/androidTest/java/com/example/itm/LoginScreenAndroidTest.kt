package com.example.itm

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.itm.ui.screens.ForgotPasswordScreen.ForgotPasswordScreen
import com.example.itm.ui.screens.LoginScreen.LoginScreen
import com.example.itm.ui.screens.OverviewScreen.OverviewScreen
import com.example.itm.ui.screens.PatientSearchScreen.PatientSearchScreen
import com.example.itm.ui.screens.Screens
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class LoginScreenAndroidTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun login_screen_exists() {
        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context)

        rule.setContent { LoginScreen(context, navController).Create() }

        rule.onNodeWithText("Log in").assertExists()
    }

    @Test
    fun login_successful() {
        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        rule.setContent {
            NavHost(navController = navController, startDestination = "authGraph") {
                navigation(route = "overviewGraph", startDestination = Screens.OverviewScreen.route) {

                    composable(route = Screens.OverviewScreen.route) {
                        OverviewScreen(LocalContext.current, navController).Create()
                    }
                }

                navigation(route = "authGraph", startDestination = Screens.LoginScreen.route) {

                    composable(route = Screens.LoginScreen.route) {
                        LoginScreen(LocalContext.current, navController).Create()
                    }
                }
            }
        }

        rule.onNodeWithTag("username").performTextInput("johndoe")
        rule.onNodeWithTag("password").performTextInput("Test.123")

        rule.onNodeWithText("Log in").performClick()

        sleep(500)
        rule.onNodeWithText("Incorrect credentials").assertDoesNotExist()
    }

    @Test
    fun login_unsuccessful_due_to_incorrect_credentials() {
        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context)

        rule.setContent { LoginScreen(context, navController).Create() }

        rule.onNodeWithTag("username").performTextInput("johndoe")
        rule.onNodeWithTag("password").performTextInput("test.123")

        rule.onNodeWithText("Log in").performClick()

        sleep(500)
        rule.onNodeWithText("Incorrect credentials").assertExists()
    }

}