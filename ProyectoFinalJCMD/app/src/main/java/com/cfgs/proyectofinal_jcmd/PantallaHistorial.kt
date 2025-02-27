package com.cfgs.proyectofinal_jcmd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaHistorial(navController: NavController, hechizosLanzados: List<String>) {
    // Creamos una columna para organizar los elementos en vertical.
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),  // Ocupa todo el tamaño disponible y agrega padding alrededor de la columna.
        verticalArrangement = Arrangement.Center,  // Centra los elementos de la columna en el eje vertical.
        horizontalAlignment = Alignment.CenterHorizontally  // Centra los elementos de la columna en el eje horizontal.
    ) {
        // Texto en la parte superior de la pantalla, que indica "Historial de Hechizos".
        Text(text = "Historial de Hechizos")

        // Agregamos un espacio entre el título y el contenido de la lista.
        Spacer(modifier = Modifier.height(16.dp))

        // Creamos una lista de hechizos lanzados que puede desplazarse de manera eficiente.
        LazyColumn {
            // Usamos items() para recorrer todos los hechizos lanzados en la lista y mostrar cada uno en una tarjeta.
            items(hechizosLanzados) { hechizo ->
                // Cada hechizo se muestra dentro de una tarjeta.
                Card(modifier = Modifier.padding(8.dp)) {  // Añadimos padding alrededor de cada tarjeta.
                    // Mostramos el hechizo dentro de la tarjeta.
                    Text(text = hechizo, modifier = Modifier.padding(16.dp))  // Añadimos padding alrededor del texto.
                }
            }
        }

        // Agregamos un espacio entre la lista y el botón de volver.
        Spacer(modifier = Modifier.height(16.dp))

        // Botón que permite navegar de vuelta a la pantalla de hechizos.
        Button(onClick = { navController.navigate("pantalla_principal") }) {
            // Texto dentro del botón que indica "Volver a Hechizos".
            Text(text = "Volver a Hechizos")
        }
    }
}