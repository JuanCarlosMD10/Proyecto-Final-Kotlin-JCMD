package com.cfgs.proyectofinal_jcmd.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF6200EE),
    secondary = androidx.compose.ui.graphics.Color(0xFF03DAC5),
    background = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    surface = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
)

private val DarkColors = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFFBB86FC),
    secondary = androidx.compose.ui.graphics.Color(0xFF03DAC5),
    background = androidx.compose.ui.graphics.Color(0xFF121212),
    surface = androidx.compose.ui.graphics.Color(0xFF121212)
)

@Composable
fun StretchItTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (androidx.compose.runtime.remember { true }) LightColors else DarkColors,
        content = content
    )
}