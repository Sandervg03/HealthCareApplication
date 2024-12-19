package com.example.itm.ui.screens.PatientSearchScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.User
import com.example.itm.session.Session
import com.example.itm.ui.composables.Errors.ShakeEffect
import com.example.itm.ui.composables.Navigation.TopBar
import com.example.itm.ui.screens.Screens
import com.example.itm.viewmodel.user.UserViewModel

class PatientSearchScreen(
    private val context: Context,
    private val navController: NavHostController,
    private val viewModel: UserViewModel =
        UserViewModel(context, navController)
) {

    @Composable
    fun Create() {
        Scaffold(
            content = { innerPadding -> println(innerPadding.toString())
                ScreenContent()
                      },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screens.PatientRegisterScreen.route) {
                            popUpTo(0)
                        } },
                    containerColor = Color.LightGray
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        )
    }

    @Composable
    fun ScreenContent() {

        val findUserResult: Resource<List<User>>? by viewModel.findUserResult.observeAsState()

        var input: String by remember {
            mutableStateOf("")
        }

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.95f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    },
                    title = "Lookup your patient",

                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier
                                .clickable { viewModel.findUser(input) }
                                .testTag("search")
                        )
                    },
                    isEnabled = true,
                    onValueChange = { value -> input = value }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                when(findUserResult) {
                    is Resource.Success -> {
                        val users = (findUserResult as? Resource.Success<List<User>>)?.data.orEmpty()
                        if (users.isNotEmpty()) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(10.dp)
                            ) {
                                items(users) { user ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(0.9f)
                                            .clickable {
                                                Session.selectedUser = user
                                                navController.navigate(Screens.OverviewScreen.route) {
                                                    popUpTo(0)
                                                }
                                            }
                                    ) {
                                        Text(
                                            text = "${user.firstName} ${user.lastName}",
                                            fontSize = 20.sp
                                        )
                                        Text(
                                            text = user.bsn.toString(),
                                            fontSize = 14.sp
                                        )
                                        if (user != users.last()) {
                                            HorizontalDivider(
                                                Modifier.height(3.dp),
                                                color = Color.Black
                                            )
                                            Spacer(modifier = Modifier.padding(5.dp))
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(text = "")
                        }
                    }
                    is Resource.Error -> ShakeEffect {
                        Text(text = findUserResult?.message!!, color = Color.Red)
                    }
                    is Resource.Fetching -> CircularProgressIndicator()
                    is Resource.Empty -> Text(text = "Nothing to see...")
                    else -> Text(text = "Nothing to see...")
                }
            }
        }
    }
}