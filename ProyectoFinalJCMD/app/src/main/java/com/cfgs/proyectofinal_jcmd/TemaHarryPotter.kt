package com.cfgs.proyectofinal_jcmd

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun TemaHarryPotter(content: @Composable () -> Unit) {
    // Usamos el tema material para envolver el contenido de la pantalla.
    MaterialTheme {
        // La superficie es un contenedor que aplica un fondo, bordes y otras propiedades visuales.
        Surface {
            // Aquí es donde se pasa el contenido real de la pantalla que será renderizado.
            content()  // El contenido es pasado como un parámetro a esta función composable.
        }
    }
}