# Arquitectura y Especificación Técnica: Sabor Perú en Android (Kotlin + Jetpack Compose)

Esta guía técnica traduce el sistema de diseño visual y las 6 pantallas diseñadas en Stitch a una implementación limpia en **Kotlin** con **Jetpack Compose**, siguiendo las directrices oficiales de Google y **Material 3 (M3)**.

---

## 1. Configuración de Dependencias (Gradle - Version Catalog / build.gradle.kts)

```kotlin
dependencies {
    // Jetpack Compose BOM
    val composeBom = platform("androidx.compose:compose-bom:2024.04.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Material 3 & UI
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material:material-icons-extended")

    // Jetpack Navigation & ViewModel
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Image loading (Coil)
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Coroutines & State
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
}
```

---

## 2. Sistema de Diseño (Theme & Tokens: Andean Culinary Material)

### `Color.kt`
```kotlin
package com.saborperu.ui.theme

import androidx.compose.ui.graphics.Color

val PeruvianAmberPrimary = Color(0xFFD97706)
val PeruvianAmberOnPrimary = Color(0xFFFFFFFF)
val PeruvianTerracotta = Color(0xFFB45309)
val PeruvianEarth = Color(0xFF78350F)

val AndeanSurface = Color(0xFFFEF9F1)
val AndeanSurfaceDim = Color(0xFFDED9D2)
val AndeanSurfaceContainer = Color(0xFFF8F3EB)
val AndeanSurfaceLowest = Color(0xFFFFFFFF)

val TextPrimary = Color(0xFF1E293B)
val TextSecondary = Color(0xFF64748B)
val AccentChiliRed = Color(0xFFDC2626)
val AccentAmazonGreen = Color(0xFF15803D)
```

### `Theme.kt`
```kotlin
package com.saborperu.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val SaborPeruColorScheme = lightColorScheme(
    primary = PeruvianAmberPrimary,
    onPrimary = PeruvianAmberOnPrimary,
    primaryContainer = Color(0xFFFDE68A),
    onPrimaryContainer = PeruvianEarth,
    surface = AndeanSurface,
    onSurface = TextPrimary,
    surfaceVariant = AndeanSurfaceContainer,
    onSurfaceVariant = TextSecondary,
    background = AndeanSurface,
    onBackground = TextPrimary
)

@Composable
fun SaborPeruTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SaborPeruColorScheme,
        typography = SaborPeruTypography, // Tipografía basada en Epilogue / Roboto Serif
        content = content
    )
}
```

---

## 3. Modelo de Datos y Navegación

### `Navigation.kt`
```kotlin
package com.saborperu.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object RegionExplorer : Screen("region/{regionName}") {
        fun createRoute(region: String) = "region/$region"
    }
    object RecipeDetail : Screen("recipe/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe/$recipeId"
    }
    object CookingMode : Screen("cook/{recipeId}/{stepIndex}") {
        fun createRoute(recipeId: String, step: Int) = "cook/$recipeId/$step"
    }
    object MarketList : Screen("market_list")
    object Favorites : Screen("favorites")
}
```

---

## 4. Estructura de Pantallas Implementadas en Compose

### A. Pantalla 1: Home (`HomeScreen.kt`)
- **Scaffold con NavigationBar (BottomBar)**: 4 pestañas (`Inicio`, `Regiones`, `Favoritos`, `Planificador`).
- **Header:** Saludo dinámico `"¡Buen provecho, Lucía!"` con TopAppBar y avatar de usuario.
- **SearchBar M3:** Campo de búsqueda interactivo con soporte de sugerencias y chips de filtro horizontal (`Comidas`, `Bebidas`, `Postres`).
- **LazyColumn:** 
  - `RegionCarousel`: Tarjetas clicables con fotos hero de Costa, Sierra y Selva.
  - `DailyFeaturedRecipeCard`: Card destacada del Ceviche Clásico con rating y tiempo.
  - `CategoryRow`: Categorías de Bebidas y Postres tradicionales con `LazyRow`.
  - `CulinaryTipsCard`: Tips esenciales de cocina criolla.

### B. Pantalla 2 & 5: Explorador Regional (`RegionExplorerScreen.kt`)
- **RegionTabs (TabRow):** Pestañas de cambio rápido (*Costa*, *Sierra*, *Selva*).
- **SubCategoryFilterChips:** Filtro rápido por tipo de plato (`Todo`, `Platos de fondo`, `Bebidas`, `Postres`).
- **RecipeGrid / RecipeList:** Tarjetas con `AsyncImage` (Coil), tags de dificultad, badges de tiempo y botón de navegación a la receta.

### C. Pantalla 3: Ficha Técnica (`RecipeDetailScreen.kt`)
- **CollapsingToolbar / Hero Image:** Foto inmersiva del Lomo Saltado Criollo.
- **PortionCounter:** Control interactivo de estado `var portions by remember { mutableIntStateOf(4) }` que recalcula las cantidades de ingredientes dinámicamente.
- **IngredientChecklist:** Lista con `Checkbox` M3 y estado persistente para marcar insumos listos.
- **StepByStepInstructions:** Pasos estructurados con insignias de tiempo y notas del chef Don Lucho.
- **BottomStickyBar:** Botón principal `"Iniciar Modo Cocina"` que navega a `CookingMode`.

### D. Pantalla 4: Modo Cocina Manos Libres (`CookingModeScreen.kt`)
- **Focus Mode:** Modo inmersivo sin BottomNav para evitar distracciones en el fogón.
- **TimerComponent:** Temporizador con Coroutines (`LaunchedEffect` con `delay(1000L)`) y controles de Play, Pause y +30s.
- **VoiceAssistantBadge:** Indicador visual de escucha de voz (`"Di 'Siguiente' o 'Pausar'"`).
- **NavigationControls:** Botones grandes de navegación entre pasos (`Anterior`, `Siguiente Paso`).

### E. Pantalla 6: Lista Inteligente de Mercado (`MarketListScreen.kt`)
- **Categorización:** Vista agrupada por Receta o por Pasillo (`Carnicería`, `Verdulería`, `Abarrotes`).
- **Resumen Financiero y Peso:** Card inferior con cálculo de costo estimado (`S/ 48.00`) y peso (`2.2 kg`).
- **Share Intent:** Acción para compartir ingredientes mediante `Intent.ACTION_SEND` a WhatsApp.

---

## 5. Arquitectura Recomendada (Clean Architecture + MVI/MVVM)
- **UI Layer:** Composables organizados por feature (`feature/home`, `feature/recipe`, `feature/cooking`, `feature/market`).
- **State Management:** `StateFlow` expuesto desde `ViewModel` y consumido con `collectAsStateWithLifecycle()`.
- **Domain Layer:** Casos de uso (`GetRecipesByRegionUseCase`, `CalculatePortionsUseCase`, `UpdateMarketItemStatusUseCase`).
- **Data Layer:** Room DB local para recetas offline (indispensable para cocinar sin conexión) y DataStore para preferencias del usuario.
