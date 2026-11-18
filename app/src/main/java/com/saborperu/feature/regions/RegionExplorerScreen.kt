package com.saborperu.feature.regions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.LocalBar
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.saborperu.R
import com.saborperu.data.Region
import com.saborperu.ui.components.BrandTopBar
import com.saborperu.ui.components.FilterPill
import com.saborperu.ui.components.PillTag
import com.saborperu.ui.components.RecipeFeedCard
import com.saborperu.ui.components.regionColor
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.EarthInk
import com.saborperu.ui.theme.ScrimWarm
import com.saborperu.ui.theme.SurfaceContainer
import com.saborperu.ui.theme.SurfaceContainerHigh
import com.saborperu.ui.theme.SurfaceContainerLow
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.TextSecondary

private data class RegionHero(
    val kicker: String,
    val title: String,
    val description: String,
    val imageRes: Int,
    val tags: List<String>
)

private fun heroFor(region: Region): RegionHero = when (region) {
    Region.COSTA -> RegionHero(
        kicker = "Mar & Criollismo",
        title = "Gastronomía de la Costa Peruana",
        description = "Ceviches, causas y guisos criollos con la frescura del Pacífico y el picor inconfundible del ají amarillo.",
        imageRes = R.drawable.region_costa,
        tags = listOf("Del mar a la mesa", "Ají amarillo")
    )
    Region.SIERRA -> RegionHero(
        kicker = "Cordillera & Valles",
        title = "Gastronomía de la Sierra Peruana",
        description = "Sabores milenarios de los Andes, cocinados con hierbas aromáticas como huacatay y chincho, tubérculos nativos y maíz sagrado.",
        imageRes = R.drawable.region_sierra,
        tags = listOf("Fogón de barro", "Papas nativas & maíz")
    )
    Region.SELVA -> RegionHero(
        kicker = "Oriente Mágico & Exótico",
        title = "Gastronomía de la Amazonía Peruana",
        description = "Sabores exuberantes del oriente con ají charapita, cecina con tacacho, juane de gallina y frutos exóticos como el camu camu y aguaje.",
        imageRes = R.drawable.region_selva,
        tags = listOf("Ají Charapita & Humo de Carbón", "15 recetas vivas")
    )
}

@Composable
fun RegionExplorerScreen(
    initialRegion: String,
    onOpenRecipe: (String) -> Unit,
    onOpenFavorites: () -> Unit,
    viewModel: RegionExplorerViewModel = viewModel(
        key = initialRegion,
        factory = viewModelFactory {
            initializer { RegionExplorerViewModel(initialRegion) }
        }
    )
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var showSearch by remember { mutableStateOf(false) }
    var sortMenuOpen by remember { mutableStateOf(false) }
    val hero = heroFor(state.region)
    val accent = regionColor(state.region)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        BrandTopBar(
            subtitle = "Regiones",
            onSearch = { showSearch = !showSearch },
            onProfile = onOpenFavorites
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clip(CircleShape)
                .background(SurfaceContainerLow)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Region.entries.forEach { region ->
                RegionTab(
                    label = region.label,
                    selected = state.region == region,
                    accent = regionColor(region),
                    onClick = { viewModel.onRegionSelected(region) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        if (showSearch) {
            Surface(
                color = SurfaceContainer,
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        if (state.query.isEmpty()) {
                            Text(
                                text = "Buscar en ${state.region.label}…",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary.copy(alpha = 0.75f)
                            )
                        }
                        BasicTextField(
                            value = state.query,
                            onValueChange = viewModel::onQueryChange,
                            singleLine = true,
                            textStyle = MaterialTheme.typography.bodyMedium.copy(color = EarthInk),
                            cursorBrush = SolidColor(accent),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Surface(
                        onClick = {
                            viewModel.onQueryChange("")
                            showSearch = false
                        },
                        shape = CircleShape,
                        color = SurfaceContainerHigh,
                        modifier = Modifier.size(30.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Cerrar búsqueda",
                                tint = TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            item {
                RegionHeroBanner(
                    hero = hero,
                    recipeCount = state.recipes.size,
                    accent = accent
                )
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val categories = listOf("Todo", "Platos de fondo", "Bebidas", "Postres")
                    val icons = listOf(null, Icons.Rounded.Restaurant, Icons.Rounded.LocalBar, Icons.Rounded.Cake)
                    items(categories.size) { index ->
                        FilterPill(
                            label = categories[index],
                            selected = state.selectedCategory == index,
                            onClick = { viewModel.onCategorySelected(index) },
                            leadingIcon = icons[index],
                            selectedColor = accent
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Explorar Platos",
                        style = MaterialTheme.typography.headlineSmall,
                        color = EarthInk,
                        modifier = Modifier.weight(1f)
                    )
                    PillTag(
                        text = state.recipes.size.toString(),
                        containerColor = SurfaceContainerHigh,
                        contentColor = TextSecondary
                    )
                    Spacer(Modifier.width(8.dp))
                    Box {
                        Surface(
                            onClick = { sortMenuOpen = true },
                            shape = CircleShape,
                            color = SurfaceContainerHigh
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Sort,
                                    contentDescription = null,
                                    tint = accent,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "Ordenar",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextSecondary
                                )
                                Icon(
                                    imageVector = Icons.Rounded.ExpandMore,
                                    contentDescription = "Ordenar recetas",
                                    tint = TextSecondary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        DropdownMenu(
                            expanded = sortMenuOpen,
                            onDismissRequest = { sortMenuOpen = false }
                        ) {
                            SortMode.entries.forEach { mode ->
                                DropdownMenuItem(
                                    text = { Text(mode.label, style = MaterialTheme.typography.bodyMedium) },
                                    leadingIcon = if (state.sortMode == mode) {
                                        {
                                            Icon(
                                                imageVector = Icons.Rounded.Check,
                                                contentDescription = null,
                                                tint = accent
                                            )
                                        }
                                    } else {
                                        null
                                    },
                                    onClick = {
                                        viewModel.onSortSelected(mode)
                                        sortMenuOpen = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            if (state.recipes.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No se encontraron recetas",
                            style = MaterialTheme.typography.titleMedium,
                            color = EarthInk
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Prueba otra categoría o explora todas las delicias de la ${state.region.label.lowercase()}.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            } else {
                items(items = state.recipes, key = { it.id }) { recipe ->
                    RecipeFeedCard(
                        recipe = recipe,
                        favorite = state.favorites.contains(recipe.id),
                        onOpen = { onOpenRecipe(recipe.id) },
                        onFavorite = { viewModel.toggleFavorite(recipe.id) },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RegionTab(
    label: String,
    selected: Boolean,
    accent: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = if (selected) accent else Color.Transparent,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 9.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = if (selected) SurfaceContainerLowest else TextSecondary
            )
        }
    }
}

@Composable
private fun RegionHeroBanner(
    hero: RegionHero,
    recipeCount: Int,
    accent: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .height(226.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        Image(
            painter = painterResource(hero.imageRes),
            contentDescription = hero.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ScrimWarm.copy(alpha = 0.15f),
                            ScrimWarm.copy(alpha = 0.88f)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                PillTag(
                    text = hero.kicker,
                    containerColor = accent,
                    contentColor = SurfaceContainerLowest
                )
                PillTag(
                    text = "$recipeCount recetas",
                    containerColor = ScrimWarm.copy(alpha = 0.55f),
                    contentColor = SurfaceContainerLowest
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = hero.title,
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 26.sp, lineHeight = 32.sp),
                color = SurfaceContainerLowest
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = hero.description,
                style = MaterialTheme.typography.bodyMedium,
                color = SurfaceContainerLowest.copy(alpha = 0.9f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                hero.tags.take(2).forEach { tag ->
                    PillTag(
                        text = tag,
                        containerColor = SurfaceContainerLowest.copy(alpha = 0.18f),
                        contentColor = SurfaceContainerLowest
                    )
                }
            }
        }
    }
}
