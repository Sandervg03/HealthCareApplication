package com.example.itm.ui.screens.AppointmentsScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.Appointment
import com.example.itm.data.model.Roles
import com.example.itm.data.sharedpreferences.SharedPreferencesManager
import com.example.itm.session.Session
import com.example.itm.ui.composables.AlertDialogs.AppointmentAlert
import com.example.itm.ui.composables.Navigation.BottomBar
import com.example.itm.ui.composables.Errors.ShakeEffect
import com.example.itm.ui.composables.Navigation.TopBar
import com.example.itm.ui.screens.NavItem
import com.example.itm.ui.screens.Screens
import com.example.itm.viewmodel.appointments.AppointmentsViewModel
import com.example.itm.viewmodel.interfaces.AppointmentsInterface

class AppointmentsScreen(
    val context: Context,
    val navController: NavHostController,
    val viewModel: AppointmentsInterface = AppointmentsViewModel(context)
) {

    @Composable
    fun Create() {

        val appointments: Resource<List<Appointment>>? by
        viewModel.appointments.observeAsState()

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.95f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (Session.role == Roles.MedicalProfessional) {
                    TopBar(
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        },
                        title = "${Session.selectedUser?.firstName} ${Session.selectedUser?.lastName}",
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                modifier = Modifier.clickable {
                                    navController.navigate(Screens.PatientSearchScreen.route) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        },
                        isEnabled = false
                    )
                } else {
                    TopBar(
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        },
                        title = "${Session.selectedUser?.firstName} ${Session.selectedUser?.lastName}",
                        trailingIcon = {},
                        isEnabled = false
                    )
                }

                when (appointments) {
                    is Resource.Success -> {
                        (appointments as Resource.Success<List<Appointment>>)
                            .data?.let { ScreenContent(appointments = it) } ?:
                            Text("Something went wrong, please try again later.")
                    }
                    is Resource.Error -> { ShakeEffect {
                        Text(
                            text = (appointments as Resource.Error<List<Appointment>>).message ?:
                            "An Unknown error occurred, please try again later.",
                            color = Color.Red
                        )
                    }
                    }
                    is Resource.Empty -> { Text(text = "Nothing to be seen") }
                    is Resource.Fetching -> { CircularProgressIndicator() }
                    else -> { Text(text = "Nothing to be seen") }
                }
            }
            BottomBar(
                items = listOf(
                    NavItem.Appointments,
                    NavItem.Records,
                    NavItem.Medication,
                    NavItem.Insurance,
                    NavItem.Invoice
                ),
                navController = navController
            )
        }
    }

    @Composable
    private fun ScreenContent(appointments: List<Appointment>) {
        Spacer(modifier = Modifier.padding(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(15.dp)
                )
                .background(color = Color.Gray, shape = RoundedCornerShape(15.dp))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1)
            ){
                items(appointments) { appointment ->

                    var showAlert: Boolean by remember {
                        mutableStateOf(false)
                    }

                    Spacer(modifier = Modifier.padding(40.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(10.dp)
                            .clickable {
                                showAlert = true
                            }
                    ) {
                        Text(
                            text = appointment.appointmentDateTime.toLocaleString(),
                            fontSize = 20.sp
                        )
                        Text(
                            text = appointment.clinicAddress,
                            color = Color.Gray,
                            fontSize = 15.sp
                        )
                    }
                    if (showAlert) {
                        AppointmentAlert(
                            context = context,
                            onDismiss = { showAlert = false },
                            appointment = appointment
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(50.dp))
        Button(onClick = { SharedPreferencesManager(context).removeSharedPreference("Authorization") }) {
            Text(text = "Remove Auth Token")
        }
    }
}