package com.cfgs.proyectofinal_jcmd

import PantallaPrincipal
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainActivityScreen() {
    // Creamos y recordamos el controlador de navegación para gestionar las pantallas.
    val navController = rememberNavController()

    // Creamos una lista mutable para almacenar los hechizos lanzados y la recordamos entre recomposiciones.
    val hechizosLanzados = remember { mutableStateListOf<String>() }

    // Configuramos el contenedor de la navegación (NavHost) que gestionará la navegación entre pantallas.
    NavHost(navController, startDestination = "pantalla_principal") {
        // Definimos la primera pantalla, llamada "pantalla_principal", que muestra la pantalla principal.
        composable("pantalla_principal") {
            // Llamamos a la pantalla principal, pasándole el controlador de navegación y la lista de hechizos lanzados.
            PantallaPrincipal(navController, hechizosLanzados)
        }
        // Definimos otra pantalla, llamada "pantalla_historial", que muestra el historial de hechizos.
        composable("pantalla_historial") {
            // Llamamos a la pantalla de historial, pasando el controlador de navegación y la lista de hechizos.
            PantallaHistorial(navController, hechizosLanzados)
        }
    }
}