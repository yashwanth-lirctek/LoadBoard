package com.lirctek.loadboard.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorPalette = darkColorScheme(
    primary = ToolBarAppBarDarkColor,

    background = background_night,

    surface = surface_night,
    surfaceVariant = surface_variant_night,
    onSurface = on_surface_night,
    onSurfaceVariant = on_surface_variant_night,

    tertiary = tertiary_night,
    onTertiary = on_tertiary_night,
    tertiaryContainer = tertiary_container_night,
    onTertiaryContainer = on_tertiary_container_night,

    secondary = secondary_night,
    onSecondary = on_secondary_night,
    secondaryContainer = secondary_container_night,
    onSecondaryContainer = on_secondary_container_night
)

private val LightColorPalette = lightColorScheme(
    primary = ToolBarAppBarWhiteColor,

    background = background_day,

    surface = surface_day,
    surfaceVariant = surface_variant_day,
    onSurface = on_surface_day,
    onSurfaceVariant = on_surface_variant_day,

    tertiary = tertiary_day,
    onTertiary = on_tertiary_day,
    tertiaryContainer = tertiary_container_day,
    onTertiaryContainer = on_tertiary_container_day,

    secondary = secondary_day,
    onSecondary = on_secondary_day,
    secondaryContainer = secondary_container_day,
    onSecondaryContainer = on_secondary_container_day
)

@Composable
fun LoadBoardTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = if (darkTheme) surface_night.toArgb() else surface_day.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = colors,
        content = content
    )
}