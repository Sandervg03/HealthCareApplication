package com.example.itm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.itm.repository.RepositoryObject
import com.example.itm.repository.registerRepositories
import com.example.itm.ui.composables.Navigation.ITMNavHost
import com.example.itm.ui.theme.ITMTheme

// Application Start
class MainActivity : ComponentActivity() {

    init {
        registerRepositories()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ITMTheme {
                // Modifier.fillMaxSize makes sure we use all height and width of a screen.
                // innerPadding makes sure all dimensions from different devices are taken into
                // account when calling a composable.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        ITMNavHost().ItmNavHost()
                    }
                }
            }
        }
    }
}
