package com.cfgs.proyectofinal_jcmd

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// MainActivity es la actividad principal de la aplicación.
class MainActivity : ComponentActivity() {

    // Esta es la función que se ejecuta cuando se crea la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Llamamos al método onCreate de la clase base.

        // Llamamos a la función para crear el canal de notificaciones.
        crearCanalDeNotificaciones()

        // Establecemos el contenido de la actividad usando un diseño de composables en Jetpack Compose.
        setContent {
            TemaHarryPotter {  // Aplicamos el tema Harry Potter al contenido.
                MainActivityScreen()  // Mostramos la pantalla principal de la actividad.
            }
        }
    }

    // Función privada que se encarga de crear un canal de notificaciones si es necesario.
    private fun crearCanalDeNotificaciones() {
        // Verificamos si la versión de Android es mayor o igual a Oreo (Android 8.0, API nivel 26).
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Creamos un nuevo canal de notificaciones con un ID, nombre y nivel de importancia.
            val canal = NotificationChannel(
                "hechizos_notificaciones",  // ID único para el canal.
                "Notificaciones de Hechizos",  // Nombre del canal que se verá en la configuración de notificaciones.
                NotificationManager.IMPORTANCE_HIGH  // Establecemos la importancia del canal (alto).
            )

            // Obtenemos el servicio de notificaciones del sistema.
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Creamos el canal de notificaciones en el sistema.
            manager.createNotificationChannel(canal)
        }
    }
}