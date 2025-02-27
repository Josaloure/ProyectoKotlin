package com.cfgs.entreferiasnuevo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) } // Agregamos la barra de navegación
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("welcome") { WelcomeScreen(navController) }
            composable("main") { MainScreen(navController) }
            composable("mantoncillos") { ProductScreen(navController, "Mantoncillos") }
            composable("pendientes") { ProductScreen(navController, "Pendientes") }
            composable("anillos") { ProductScreen(navController, "Anillos") }
            composable("collares") { ProductScreen(navController, "Collares") }
        }
    }
}
