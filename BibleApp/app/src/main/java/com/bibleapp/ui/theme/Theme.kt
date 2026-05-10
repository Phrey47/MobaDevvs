package com.bibleapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Amber-based palette matching the wireframe
val Amber600 = Color(0xFFD97706)
val Amber500 = Color(0xFFF59E0B)
val Amber200 = Color(0xFFFDE68A)
val Amber50  = Color(0xFFFFFBEB)

val Stone50  = Color(0xFFFAFAF9)
val Stone100 = Color(0xFFF5F5F4)
val Stone200 = Color(0xFFE7E5E4)
val Stone400 = Color(0xFFA8A29E)
val Stone800 = Color(0xFF292524)
val Stone900 = Color(0xFF1C1917)

val Zinc900  = Color(0xFF18181B)
val Zinc950  = Color(0xFF09090B)
val Zinc800  = Color(0xFF27272A)
val Zinc100  = Color(0xFFF4F4F5)

private val LightColors = lightColorScheme(
    primary = Amber600,
    onPrimary = Color.White,
    primaryContainer = Amber50,
    onPrimaryContainer = Stone900,
    secondary = Stone400,
    background = Stone50,
    surface = Color.White,
    onBackground = Stone900,
    onSurface = Stone800,
    outline = Stone200,
)

private val DarkColors = darkColorScheme(
    primary = Amber500,
    onPrimary = Stone900,
    primaryContainer = Color(0xFF451A03),
    onPrimaryContainer = Amber200,
    secondary = Color(0xFF78716C),
    background = Zinc950,
    surface = Zinc900,
    onBackground = Color(0xFFF4F4F5),
    onSurface = Color(0xFFE4E4E7),
    outline = Zinc800,
)

@Composable
fun BibleAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
