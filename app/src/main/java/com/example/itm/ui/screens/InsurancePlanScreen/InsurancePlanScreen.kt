package com.example.itm.ui.screens.InsurancePlanScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.InsurancePlan
import com.example.itm.data.model.Roles
import com.example.itm.data.sharedpreferences.SharedPreferencesManager
import com.example.itm.session.Session
import com.example.itm.ui.composables.Navigation.BottomBar
import com.example.itm.ui.composables.Navigation.TopBar
import com.example.itm.ui.screens.NavItem
import com.example.itm.ui.screens.Screens
import com.example.itm.viewmodel.insuranceplan.InsurancePlanViewModel

class InsurancePlanScreen(
    private val context: Context,
    private val navController: NavHostController,
    private val viewModel: InsurancePlanViewModel = InsurancePlanViewModel(context)
) {


    @Composable
    fun Create() {
        val insurancePlanResponse: Resource<InsurancePlan>? by
        viewModel.getInsurancePlanResult.observeAsState()

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

                when (insurancePlanResponse) {
                    is Resource.Fetching -> CircularProgressIndicator()
                    is Resource.Empty -> Text(text = "Nothing...")
                    is Resource.Error -> Text(
                        text = insurancePlanResponse?.message ?: "Something went wrong"
                    )
                    is Resource.Success ->
                        (insurancePlanResponse as Resource.Success<InsurancePlan>).data
                            ?.let { ScreenContent(it) } ?: Text(text = "Something went wrong")
                    else -> Text(text = "Something went wrong")
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
    private fun ScreenContent(insurancePlan: InsurancePlan) {
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
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(all = 10.dp)
                ) {
                    Text(text = insurancePlan.insuranceCompany, fontSize = 20.sp)
                    Text(text = insurancePlan.planType, color = Color.Gray, fontSize = 15.sp)

                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(text = insurancePlan.coverageDetails, fontSize = 20.sp)

                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(text = "Expiration:", fontSize = 20.sp)
                    Text(
                        text = insurancePlan.expirationDate.toLocaleString(),
                        color = Color.Gray, fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(text = "€${insurancePlan.premiumAmount} p/m", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
        Spacer(modifier = Modifier.padding(50.dp))
        Button(onClick = { SharedPreferencesManager(context).removeSharedPreference("Authorization") }) {
            Text(text = "Remove Auth Token")
        }
    }
}