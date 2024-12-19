package com.example.itm.ui.screens.PatientRegisterScreen

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.MaritalStatus
import com.example.itm.data.model.Roles
import com.example.itm.data.model.User
import com.example.itm.ui.composables.ImagePicker
import com.example.itm.ui.composables.InputBox
import com.example.itm.ui.composables.Navigation.TopBar
import com.example.itm.ui.screens.Screens
import com.example.itm.viewmodel.user.UserViewModel
import java.util.Date

class PatientRegisterScreen(
    private val context: Context,
    private val navController: NavHostController,
    private val viewModel: UserViewModel =
        UserViewModel(context, navController)
)  {

    @Composable
    fun Create() {
        Column {

            TopBar(
                title = "Add a new user",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screens.PatientSearchScreen.route) {
                                    popUpTo(0)
                                }
                            }
                    )
                },
                isEnabled = false
            )

            val registerUserResult: Resource<User>? by viewModel.registerUserResult.observeAsState()
            when (registerUserResult) {
                is Resource.Success -> {}
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        (registerUserResult as Resource.Error<User>).message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Resource.Fetching -> {
                    CircularProgressIndicator()
                }
                else -> {}
            }
            ScreenContent()
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ScreenContent() {

        var bsn: Int by remember {
            mutableStateOf(1)
        }

        var firstName: String by remember {
            mutableStateOf("")
        }

        var lastName: String by remember {
            mutableStateOf("")
        }

        var username: String by remember {
            mutableStateOf("")
        }

        var password: String by remember {
            mutableStateOf("")
        }

        var phoneNumber: String by remember {
            mutableStateOf("")
        }

        var emergencyContactName: String by remember {
            mutableStateOf("")
        }

        var emergencyPhoneNumber: String by remember {
            mutableStateOf("")
        }

        var address: String by remember {
            mutableStateOf("")
        }

        var countryOfResidence: String by remember {
            mutableStateOf("")
        }

        var insurancePolicy: String by remember {
            mutableStateOf("")
        }

        var maritalStatus: MaritalStatus? by remember {
            mutableStateOf<MaritalStatus?>(null)
        }

        var dateOfBirth: Date? by remember {
            mutableStateOf<Date?>(null)
        }
        
        var profilePicture: ByteArray? by remember {
            mutableStateOf<ByteArray?>(null)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    InputBox(
                        title = "Bsn"
                    ) {
                        TextField(
                            value = bsn.toString(),
                            onValueChange = {
                                if (it.toIntOrNull() != null) {
                                    bsn = it.toInt()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Only numbers are allowed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "First name"
                    ) {
                        TextField(
                            value = firstName,
                            onValueChange = {
                                firstName = it
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Last name"
                    ) {
                        TextField(
                            value = lastName,
                            onValueChange = {
                                lastName = it
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Username"
                    ) {
                        TextField(
                            value = username,
                            onValueChange = {
                                username = it
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Password"
                    ) {
                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Phone number"
                    ) {
                        TextField(
                            value = phoneNumber,
                            onValueChange = {
                                if (it.toIntOrNull() != null) {
                                    phoneNumber = it
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Only numbers are allowed!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Emergency contact name"
                    ) {
                        TextField(
                            value = emergencyContactName,
                            onValueChange = { emergencyContactName = it }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Emergency phone number"
                    ) {
                        TextField(
                            value = emergencyPhoneNumber,
                            onValueChange = {
                                if (it.toIntOrNull() != null) {
                                    emergencyPhoneNumber = it
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Only numbers are allowed!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Address"
                    ) {
                        TextField(
                            value = address,
                            onValueChange = {
                                address = it
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Country of residence"
                    ) {
                        TextField(
                            value = countryOfResidence,
                            onValueChange = {
                                countryOfResidence = it
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Insurance policy"
                    ) {
                        TextField(
                            value = insurancePolicy,
                            onValueChange = {
                                insurancePolicy = it
                            }
                        )
                    }
                }
                item {
                    InputBox(
                        title = "Marital status"
                    ) {
                        var expanded by remember { mutableStateOf(false) }

                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }
                        ) {
                            TextField(
                                value = maritalStatus?.name ?: "",
                                onValueChange = {},
                                readOnly = true,
                                placeholder = { Text(text = "Marital status")},
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                                },
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                MaritalStatus.entries.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(text = option.name) },
                                        onClick = {
                                            maritalStatus = option
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    InputBox(
                        title = "Date of birth"
                    ) {
                        var isDatePickerVisible: Boolean by remember {
                            mutableStateOf(false)
                        }
                        
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { isDatePickerVisible = true },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                            ) {
                                Text(text = "Pick a date")
                            }
                            dateOfBirth?.toLocaleString()?.let { Text(text = it) }
                        }
                        
                        if (isDatePickerVisible) {
                            DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    // Update the selected date
                                    dateOfBirth = Date(year - 1900, month, dayOfMonth)
                                    isDatePickerVisible = false
                                },
                                2024, // Default year
                                0,    // Default month (January)
                                1     // Default day
                            ).show()
                        }
                    }
                }
                item {
                    InputBox(
                        title = "Profile picture"
                    ) {
                        ImagePicker(
                            onSelect = { image ->
                                profilePicture = image
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))
            
            Button(
                onClick = {
                    viewModel.registerUser(
                        User(
                            bsn = bsn,
                            firstName = firstName,
                            lastName = lastName,
                            dateOfBirth = dateOfBirth ?: Date(),
                            userName = username,
                            password = password,
                            emergencyContactName = emergencyContactName,
                            emergencyContactPhoneNumber = emergencyPhoneNumber,
                            profilePicture = profilePicture ?: ByteArray(1),
                            accountCreationDate = Date(),
                            address = address,
                            countryOfResidence = countryOfResidence,
                            insurancePolicy = insurancePolicy,
                            maritalStatus = maritalStatus ?: MaritalStatus.Single,
                            phoneNumber = phoneNumber,
                            lastLogin = Date(),
                            role = Roles.Patient
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Add user")
            }
        }
    }
}