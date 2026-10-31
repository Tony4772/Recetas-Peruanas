package com.andeanculinary.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = AjiGoldPrimary,
    onPrimary = OnAjiGold,
    primaryContainer = AjiGoldContainer,
    onPrimaryContainer = OnAjiGoldContainer,
    secondary = AnticuchoRustSecondary,
    onSecondary = OnAnticuchoRust,
    secondaryContainer = AnticuchoRustContainer,
    onSecondaryContainer = OnAnticuchoRustContainer,
    tertiary = MaizMoradoTertiary,
    onTertiary = OnMaizMorado,
    tertiaryContainer = MaizMoradoContainer,
    onTertiaryContainer = OnMaizMoradoContainer,
    background = SurfaceBase,
    onBackground = OnSurfaceText,
    surface = SurfaceBase,
    onSurface = OnSurfaceText,
    surfaceVariant = SurfaceContainerHigh,
    onSurfaceVariant = OnSurfaceVariantText,
    outline = OutlineWarm,
    outlineVariant = OutlineVariantWarm
)

private val DarkColorScheme = darkColorScheme(
    primary = AjiGoldPrimary,
    onPrimary = OnAjiGold,
    secondary = AnticuchoRustSecondary,
    onSecondary = OnAnticuchoRust,
    tertiary = MaizMoradoLight,
    onTertiary = OnMaizMorado,
    background = DarkSurfaceBase,
    onBackground = DarkOnSurface,
    surface = DarkSurfaceBase,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceContainerHigh,
    onSurfaceVariant = DarkOnSurfaceVariant
)

@Composable
fun AndeanCulinaryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.surface.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AndeanTypography,
        content = content
    )
}
