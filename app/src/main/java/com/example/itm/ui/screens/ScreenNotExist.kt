package com.example.itm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.itm.R
import com.example.itm.ui.composables.Navigation.safeNavigate

class ScreenNotExist(val navController: NavHostController) {

    @Composable
    fun Create() {
        
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "404", 
                color = colorResource(id = R.color.darker_purple),
                fontSize = 80.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "Page does not exist",
                color = colorResource(id = R.color.darker_purple),
                fontSize = 40.sp
                )
            Spacer(modifier = Modifier.padding(100.dp))
            Button(
                onClick = {
                    safeNavigate(navController, "overviewGraph")
                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, backgroundColor = Color.Blue)
            ) {
                Text(text = "Go back to overview")
            }
        }
    }
}