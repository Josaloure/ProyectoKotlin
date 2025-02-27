package com.cfgs.entreferiasnuevo

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun ProductScreen(navController: NavController, category: String) {
    val products = getProductsByCategory(category)
    val context = LocalContext.current
    var cartCount by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }
    /**Temporizador de 1 minuto*/
    LaunchedEffect(Unit) {
        delay(60000)
        Toast.makeText(context, "Llevas 1 minuto... ", Toast.LENGTH_LONG).show()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6FA))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = category,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6A0DAD)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Productos en el carrito: $cartCount",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4B0082)
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = product.imageRes),
                            contentDescription = product.name,
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = product.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4B0082),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Precio: ${product.price}€",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                cartCount++
                                Toast.makeText(
                                    context,
                                    "${product.name} añadido al carrito",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text("Añadir al Carrito")
                        }
                    }
                }
            }
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}

/**Función que devuelve los productos según la categoría */
fun getProductsByCategory(category: String): List<Product> {
    return when (category) {
        "Mantoncillos" -> listOf(
            Product("Mantoncillo Verde-Jabonoso",72 , R.drawable.mantoncillo1),
            Product("Mantoncillo Fucsia", 70, R.drawable.mantoncillo2),
            Product("Mantoncillo Verde-Agua", 22, R.drawable.mantoncillo3),
            Product("Mantoncillo Negro", 70, R.drawable.mantoncillo4)
        )
        "Pendientes" -> listOf(
            Product("Pendientes Aros Dorados", 15, R.drawable.pendientes1),
            Product("Pendientes Aros Dorados-Plateados", 13, R.drawable.pendientes2),
            Product("Pendientes Aros Ovalados", 15, R.drawable.pendientes3),
            Product("Pendientes Rojos", 12, R.drawable.pendientes4)
        )
        "Anillos" -> listOf(
            Product("Anillo Perla Negra", 8, R.drawable.anillo1),
            Product("Anillo Piedra Morada", 9, R.drawable.anillo2),
            Product("Anillo Ovalado Morado", 10, R.drawable.anillo3),
            Product("Anillo Ajustable Dorado", 11, R.drawable.anillo4)
        )
        "Collares" -> listOf(
            Product("Collar Virgen Niña", 13, R.drawable.collar1),
            Product("Cadena Acero Dorada", 15, R.drawable.collar2),
            Product("Collar Tres Cruces", 15, R.drawable.collar3),
            Product("Collar Escapulario", 16, R.drawable.collar4)
        )
        else -> emptyList()
    }
}

/**Modelo de datos para los productos*/
data class Product(val name: String, val price: Int, val imageRes: Int)
