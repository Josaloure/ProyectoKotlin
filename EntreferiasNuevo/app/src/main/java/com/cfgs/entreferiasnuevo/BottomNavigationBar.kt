package com.cfgs.entreferiasnuevo


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color(0xFF6A1B9A),
        contentColor = Color.White
    ) {
        val items = listOf("Inicio", "Mantoncillos", "Pendientes", "Anillos", "Collares")
        items.forEach { item ->
            TextButton(onClick = {
                val route = if (item == "Inicio") "welcome" else item.lowercase()
                navController.navigate(route)
            }) {
                Text(item, color = Color.White)
            }
        }
    }
}

