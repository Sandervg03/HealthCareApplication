package com.example.itm.ui.composables.Navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.example.itm.ui.screens.Screens

fun safeNavigate(navController: NavHostController, destination: String) {
    val destinationExists = destinationExistsInGraph(navController.graph, destination)
    if (destinationExists) {
        navController.navigate(destination) {
            popUpTo(0) { inclusive = true }
        }
    } else {
        navController.navigate(Screens.ScreenNotExist.route) {
            popUpTo(0) { inclusive = true }
        }
    }
}

fun destinationExistsInGraph(graph: NavGraph, destination: String): Boolean {
    for (node in graph) {
        if (node is NavDestination && node.route == destination) {
            return true
        }
        if (node is NavGraph) {
            if (destinationExistsInGraph(node, destination)) {
                return true
            }
        }
    }
    return false
}