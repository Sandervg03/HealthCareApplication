package com.example.itm.ui.composables.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.itm.ui.screens.NavItem

@Composable
fun BottomBar(
    items: List<NavItem>,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.description,
                modifier = Modifier
                    .let {
                        if (navController.currentDestination?.route == item.clickDestination) {
                            it.background(Color.LightGray, CircleShape)
                        } else {
                            it
                        }
                    }
                    .clickable {
                        safeNavigate(navController = navController, destination = item.clickDestination)
                    }
                    .height(60.dp)
            )
        }
    }
}