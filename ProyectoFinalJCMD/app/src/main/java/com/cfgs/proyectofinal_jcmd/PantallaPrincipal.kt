import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun PantallaPrincipal(navController: NavController, hechizosLanzados: MutableList<String>) {
    // Obtenemos el contexto actual de la aplicación para poder usarlo en funciones posteriores (notificaciones, temporizadores, etc.)
    val contexto = LocalContext.current

    // Definimos una lista de hechizos con su nombre y descripción.
    val hechizos = listOf(
        "Expelliarmus" to "Desarma al oponente.",
        "Lumos" to "Enciende la punta de la varita.",
        "Alohomora" to "Abre cerraduras.",
        "Expecto Patronum" to "Convoca un Patronus para protegerte de Dementores.",
        "Wingardium Leviosa" to "Levanta objetos y los hace flotar."
    )

    // Variables que gestionan el estado de la interfaz (hechizo en curso, tiempo restante y estado de cuenta regresiva).
    var hechizoActual by remember { mutableStateOf<String?>(null) }  // Guarda el hechizo que está en cuenta regresiva.
    var tiempoRestante by remember { mutableStateOf(0) }  // Guarda el tiempo restante para el hechizo actual.
    var enCuentaRegresiva by remember { mutableStateOf(false) }  // Indica si hay una cuenta regresiva activa.

    // Columna que organiza los elementos verticalmente en la pantalla.
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,  // Centra los elementos en el eje vertical.
        horizontalAlignment = Alignment.CenterHorizontally  // Centra los elementos en el eje horizontal.
    ) {
        // Título de la sección
        Text(text = "Hechizos Disponibles")

        // Espacio entre el título y la lista de hechizos.
        Spacer(modifier = Modifier.height(16.dp))

        // Lista que muestra los hechizos disponibles utilizando LazyColumn (desplazable).
        LazyColumn {
            // Recorremos la lista de hechizos y mostramos un botón por cada uno.
            items(hechizos) { (hechizo, descripcion) ->
                Button(
                    onClick = {
                        // Si no estamos en cuenta regresiva, comenzamos una nueva cuenta atrás para el hechizo seleccionado.
                        if (!enCuentaRegresiva) {
                            hechizoActual = hechizo  // Establecemos el hechizo actual.
                            enCuentaRegresiva = true  // Indicamos que hay una cuenta regresiva.
                            // Llamamos a la función para iniciar la cuenta atrás.
                            iniciarCuentaAtras(hechizo, descripcion, contexto, hechizosLanzados) { nuevoTiempoRestante ->
                                tiempoRestante = nuevoTiempoRestante  // Actualizamos el tiempo restante mostrado.
                            }
                        } else {
                            // Si ya hay una cuenta regresiva, simplemente cambiamos el hechizo actual.
                            hechizoActual = hechizo
                            // Iniciamos una nueva cuenta atrás para el nuevo hechizo.
                            iniciarCuentaAtras(hechizo, descripcion, contexto, hechizosLanzados) { nuevoTiempoRestante ->
                                tiempoRestante = nuevoTiempoRestante
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),  // Establecemos el tamaño y el relleno del botón.
                ) {
                    Text(text = hechizo)  // Mostramos el nombre del hechizo en el botón.
                }
            }
        }

        // Si hay un hechizo actual en cuenta regresiva, mostramos el mensaje de cuenta atrás.
        hechizoActual?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Lanzando $it en $tiempoRestante segundos...")  // Indicamos el hechizo y el tiempo restante.
        }

        // Espacio entre la cuenta regresiva y el botón de historial.
        Spacer(modifier = Modifier.height(16.dp))

        // Botón que permite navegar a la pantalla de historial de hechizos.
        Button(onClick = { navController.navigate("pantalla_historial") }) {
            Text(text = "Ver Historial de Hechizos")  // Texto dentro del botón.
        }
    }
}

// Función para iniciar la cuenta atrás para un hechizo seleccionado.
@SuppressLint("MissingPermission")  // Suprime la advertencia sobre permisos (en este caso es solo para notificaciones).
fun iniciarCuentaAtras(
    hechizo: String,
    descripcion: String,
    context: Context,
    hechizosLanzados: MutableList<String>,
    onFinish: (Int) -> Unit // Callback para actualizar el tiempo restante.
) {
    object : CountDownTimer(3000, 1000) {  // El temporizador durará 3000 ms (3 segundos), con una actualización cada segundo (1000 ms).
        override fun onTick(millisUntilFinished: Long) {
            val nuevoTiempoRestante = (millisUntilFinished / 1000).toInt()  // Calculamos el tiempo restante.
            onFinish(nuevoTiempoRestante)  // Actualizamos el tiempo restante.
        }

        override fun onFinish() {
            // Al finalizar la cuenta regresiva, enviamos una notificación y agregamos el hechizo al historial.
            enviarNotificacion(context, hechizo, descripcion)
            hechizosLanzados.add(hechizo)  // Añadimos el hechizo lanzado al historial.
            onFinish(0)  // Actualizamos el tiempo restante a 0.
        }
    }.start()  // Iniciamos el temporizador.
}

// Función para enviar una notificación al usuario una vez que se ha lanzado un hechizo.
@SuppressLint("MissingPermission")
fun enviarNotificacion(context: Context, titulo: String, contenido: String) {
    val idNotificacion = Random.nextInt(1000)  // Generamos un ID aleatorio para la notificación.
    val notificacion = NotificationCompat.Builder(context, "hechizos_notificaciones")
        .setSmallIcon(android.R.drawable.ic_dialog_info)  // Icono de la notificación.
        .setContentTitle(titulo)  // Título de la notificación.
        .setContentText(contenido)  // Descripción de la notificación.
        .setPriority(NotificationCompat.PRIORITY_HIGH)  // Prioridad alta para la notificación.
        .build()

    // Enviamos la notificación utilizando el NotificationManager.
    NotificationManagerCompat.from(context).notify(idNotificacion, notificacion)
    Log.d("Notificacion", "Enviada: $titulo")  // Logueamos el envío de la notificación.
}