package com.example.itm.ui.composables.Navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.itm.ui.screens.AppointmentsScreen.AppointmentsScreen
import com.example.itm.ui.screens.ForgotPasswordScreen.ForgotPasswordScreen
import com.example.itm.ui.screens.InsurancePlanScreen.InsurancePlanScreen
import com.example.itm.ui.screens.LoginScreen.LoginScreen
import com.example.itm.ui.screens.OverviewScreen.OverviewScreen
import com.example.itm.ui.screens.PatientRegisterScreen.PatientRegisterScreen
import com.example.itm.ui.screens.PatientSearchScreen.PatientSearchScreen
import com.example.itm.ui.screens.ScreenNotExist
import com.example.itm.ui.screens.Screens
import com.example.itm.util.authorizationevent.UnauthorizedEvent
import com.example.itm.util.provider.GetOriginalScreen

class ITMNavHost {

    // Navigation host of the application.

    @Composable
    fun ItmNavHost() {
        var startDestination: String? by remember {
            mutableStateOf(null)
        }

        val context = LocalContext.current

        val navController: NavHostController = rememberNavController()

        // Gets the original screen to show when the app is started.
        // Also adds a collector to the unauthorizedEvent variable.
        // If an unauthorized status code is returned, the user is
        // sent to the login screen and the previous screens are wiped
        // from the navigation backstack.
        LaunchedEffect(Unit) {
            startDestination = GetOriginalScreen.get(context)

            UnauthorizedEvent.unauthorizedEvent.collect {
                navController.navigate(Screens.LoginScreen.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }

        if (startDestination != null) {
            NavHost(
                navController = navController,
                startDestination = startDestination!!
            ) {

                navigation(route = "authGraph", startDestination = Screens.LoginScreen.route) {

                    composable(route = Screens.LoginScreen.route) {
                        LoginScreen(LocalContext.current, navController).Create()
                    }

                    composable(route = Screens.ForgotPasswordScreen.route) {
                        ForgotPasswordScreen().Create()
                    }
                }

                navigation(
                    route = "overviewGraph",
                    startDestination = Screens.OverviewScreen.route
                ) {

                    composable(route = Screens.OverviewScreen.route) {
                        OverviewScreen(LocalContext.current, navController).Create()
                    }

                    composable(route = Screens.PatientSearchScreen.route) {
                        PatientSearchScreen(LocalContext.current, navController).Create()
                    }

                    composable(route = Screens.PatientRegisterScreen.route) {
                        PatientRegisterScreen(LocalContext.current, navController).Create()
                    }

                    composable(route = Screens.InsurancePlanScreen.route) {
                        InsurancePlanScreen(LocalContext.current, navController).Create()
                    }

                    composable(route = Screens.AppointmentsScreen.route) {
                        AppointmentsScreen(LocalContext.current, navController).Create()
                    }
                }

                composable(route = Screens.ScreenNotExist.route) {
                    ScreenNotExist(navController).Create()
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}