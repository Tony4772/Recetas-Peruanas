package com.saborperu.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val ColorInverseSurface = androidx.compose.ui.graphics.Color(0xFF32302B)
private val ColorInverseOnSurface = androidx.compose.ui.graphics.Color(0xFFF5F0E8)

private val SaborPeruColorScheme = lightColorScheme(
    primary = AjiAmarillo,
    onPrimary = SurfaceContainerLowest,
    primaryContainer = AjiAmarilloContainer,
    onPrimaryContainer = SurfaceContainerLowest,
    secondary = Terracotta,
    onSecondary = SurfaceContainerLowest,
    secondaryContainer = TerracottaBright,
    onSecondaryContainer = OnTerracottaFixed,
    tertiary = MaizMorado,
    onTertiary = SurfaceContainerLowest,
    tertiaryContainer = MaizMorado,
    onTertiaryContainer = SurfaceContainerLowest,
    background = AndeanSurface,
    onBackground = EarthInk,
    surface = AndeanSurface,
    onSurface = EarthInk,
    surfaceVariant = SurfaceContainerHighest,
    onSurfaceVariant = TextSecondary,
    surfaceTint = AjiAmarillo,
    inverseSurface = ColorInverseSurface,
    inverseOnSurface = ColorInverseOnSurface,
    outline = OutlineWarm,
    outlineVariant = OutlineVariant,
    error = ErrorRed,
    onError = SurfaceContainerLowest,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    surfaceDim = SurfaceDim,
    surfaceBright = AndeanSurface
)

private val SaborPeruShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun SaborPeruTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SaborPeruColorScheme,
        typography = SaborPeruTypography,
        shapes = SaborPeruShapes,
        content = content
    )
}
