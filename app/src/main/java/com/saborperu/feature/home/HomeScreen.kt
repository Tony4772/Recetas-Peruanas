package com.saborperu.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingBasket
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.WineBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saborperu.R
import com.saborperu.data.Recipe
import com.saborperu.data.RecipeCategory
import com.saborperu.data.RecipeRepository
import com.saborperu.data.Region
import com.saborperu.ui.components.BrandTopBar
import com.saborperu.ui.components.CompactRecipeCard
import com.saborperu.ui.components.FavoriteCircleButton
import com.saborperu.ui.components.FilterPill
import com.saborperu.ui.components.MetaItem
import com.saborperu.ui.components.PillTag
import com.saborperu.ui.components.PrimaryPillButton
import com.saborperu.ui.components.RatingPill
import com.saborperu.ui.components.SectionHeader
import com.saborperu.ui.components.SpiceGauge
import com.saborperu.ui.components.TipCard
import com.saborperu.ui.components.regionColor
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.AjiAmarilloLight
import com.saborperu.ui.theme.EarthInk
import com.saborperu.ui.theme.MaizMorado
import com.saborperu.ui.theme.MaizMoradoFixed
import com.saborperu.ui.theme.OnAjiAmarilloLight
import com.saborperu.ui.theme.OnMaizMoradoFixed
import com.saborperu.ui.theme.OnTerracottaFixed
import com.saborperu.ui.theme.ScrimWarm
import com.saborperu.ui.theme.SurfaceContainer
import com.saborperu.ui.theme.SurfaceContainerHigh
import com.saborperu.ui.theme.SurfaceContainerLow
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.Terracotta
import com.saborperu.ui.theme.TerracottaFixed
import com.saborperu.ui.theme.TextSecondary

private data class RegionCard(
    val region: Region,
    val recipeCount: String,
    val description: String,
    val imageRes: Int
)

private val regionCards = listOf(
    RegionCard(
        Region.COSTA,
        "20 recetas",
        "Ceviches, causas y guisos criollos marinados con ají amarillo.",
        R.drawable.region_costa
    ),
    RegionCard(
        Region.SIERRA,
        "17 recetas",
        "Pachamancas, truchas y aromas andinos ancestrales.",
        R.drawable.region_sierra
    ),
    RegionCard(
        Region.SELVA,
        "15 recetas",
        "Juane, cecina con tacacho y ají de cocona silvestre.",
        R.drawable.region_selva
    )
)

@Composable
fun HomeScreen(
    onOpenRecipe: (String) -> Unit,
    onOpenRegion: (String) -> Unit,
    onOpenMarket: () -> Unit,
    onOpenFavorites: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }

    val featured = when (state.selectedFilter) {
        1 -> RecipeRepository.recipeById("pisco-sour")
        2 -> RecipeRepository.recipeById("suspiro-limena")
        else -> RecipeRepository.recipeById("ceviche-limeno")
    } ?: RecipeRepository.recipes.first()

    val searchResults = remember(state.query) {
        val text = state.query.trim()
        if (text.isBlank()) {
            emptyList()
        } else {
            RecipeRepository.recipes.filter { recipe ->
                recipe.name.contains(text, ignoreCase = true) ||
                    recipe.description.contains(text, ignoreCase = true) ||
                    recipe.region.label.contains(text, ignoreCase = true) ||
                    recipe.tags.any { it.contains(text, ignoreCase = true) }
            }.take(4)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        BrandTopBar(
            subtitle = "Inicio",
            onSearch = { focusRequester.requestFocus() },
            onProfile = onOpenFavorites
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "¡Buen provecho!",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontSize = 28.sp,
                                lineHeight = 34.sp
                            ),
                            color = EarthInk
                        )
                        Text(
                            text = "Descubre el auténtico sabor del Perú",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(TerracottaFixed),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.RestaurantMenu,
                            contentDescription = null,
                            tint = OnTerracottaFixed,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Surface(
                        color = SurfaceContainer,
                        shape = CircleShape,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 16.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = null,
                                tint = TextSecondary,
                                modifier = Modifier.size(22.dp)
                            )
                            Box(modifier = Modifier.weight(1f)) {
                                if (state.query.isEmpty()) {
                                    Text(
                                        text = "¿Qué antojo tienes hoy? (p. ej. Lomo Saltado)",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = TextSecondary.copy(alpha = 0.75f),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                BasicTextField(
                                    value = state.query,
                                    onValueChange = viewModel::onQueryChange,
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = EarthInk),
                                    cursorBrush = SolidColor(AjiAmarillo),
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .focusRequester(focusRequester)
                                )
                            }
                            Surface(
                                onClick = {
                                    viewModel.onFilterSelected((state.selectedFilter + 1) % 4)
                                },
                                shape = CircleShape,
                                color = SurfaceContainerHigh,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Rounded.Tune,
                                        contentDescription = "Cambiar filtro",
                                        tint = TextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }

                    if (state.query.isNotBlank()) {
                        Spacer(Modifier.height(8.dp))
                        Surface(
                            color = SurfaceContainerLowest,
                            shape = RoundedCornerShape(18.dp),
                            shadowElevation = 3.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(vertical = 6.dp)) {
                                if (searchResults.isEmpty()) {
                                    Text(
                                        text = "No encontramos recetas para \"${state.query}\".",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = TextSecondary,
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                                    )
                                } else {
                                    searchResults.forEach { recipe ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable { onOpenRecipe(recipe.id) }
                                                .padding(horizontal = 12.dp, vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(
                                                painter = painterResource(recipe.imageRes),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(44.dp)
                                                    .clip(RoundedCornerShape(10.dp))
                                            )
                                            Spacer(Modifier.width(10.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = recipe.name,
                                                    style = MaterialTheme.typography.titleSmall,
                                                    color = EarthInk,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                                Text(
                                                    text = "${recipe.region.label} • ${recipe.prepMinutes} min",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = TextSecondary
                                                )
                                            }
                                            Icon(
                                                imageVector = Icons.Rounded.ChevronRight,
                                                contentDescription = null,
                                                tint = TextSecondary,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val filters = listOf("Comidas", "Bebidas", "Postres", "Fácil (<30 min)")
                    items(filters.size) { index ->
                        FilterPill(
                            label = filters[index],
                            selected = state.selectedFilter == index,
                            onClick = { viewModel.onFilterSelected(index) },
                            leadingIcon = if (index == 3) Icons.Rounded.Timer else null
                        )
                    }
                }
            }

            item {
                SectionHeader(
                    title = "Explora por Región",
                    icon = Icons.Rounded.ChevronRight,
                    actionText = "Ver mapa",
                    onAction = { onOpenRegion("Costa") }
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(regionCards) { card ->
                        RegionCarouselCard(card = card, onClick = { onOpenRegion(card.region.label) })
                    }
                }
            }

            item {
                Spacer(Modifier.height(12.dp))
                SectionHeader(
                    title = "Receta del Día",
                    icon = Icons.Rounded.LocalFireDepartment,
                    iconTint = Terracotta,
                    actionText = "Destacado",
                    onAction = { onOpenRecipe(featured.id) }
                )
            }

            item {
                FeaturedRecipeCard(
                    recipe = featured,
                    favorite = state.favorites.contains(featured.id),
                    onOpen = { onOpenRecipe(featured.id) },
                    onFavorite = { viewModel.toggleFavorite(featured.id) }
                )
            }

            item {
                Spacer(Modifier.height(8.dp))
                SectionHeader(
                    title = "Categorías Populares",
                    icon = Icons.Rounded.Category,
                    iconTint = MaizMorado
                )
            }

            item {
                CategoryGroup(
                    title = "Bebidas Emblemáticas",
                    icon = Icons.Rounded.WineBar,
                    total = RecipeRepository.recipes.count { it.category == RecipeCategory.BEBIDA },
                    recipes = RecipeRepository.popularBeverages(),
                    onOpenRecipe = onOpenRecipe,
                    onSeeAll = { onOpenRegion("Costa") }
                )
            }

            item {
                Spacer(Modifier.height(10.dp))
                CategoryGroup(
                    title = "Postres Tradicionales",
                    icon = Icons.Rounded.Cake,
                    total = RecipeRepository.recipes.count { it.category == RecipeCategory.POSTRE },
                    recipes = RecipeRepository.popularDesserts(),
                    onOpenRecipe = onOpenRecipe,
                    onSeeAll = { onOpenRegion("Sierra") }
                )
            }

            item {
                Spacer(Modifier.height(12.dp))
                CulinaryTipsCard()
            }

            item {
                Spacer(Modifier.height(12.dp))
                Surface(
                    onClick = onOpenMarket,
                    color = SurfaceContainerLow,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(AjiAmarilloLight),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ShoppingBasket,
                                contentDescription = null,
                                tint = AjiAmarillo,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Lista del Mercado",
                                style = MaterialTheme.typography.titleMedium,
                                color = EarthInk
                            )
                            Text(
                                text = "Organiza tus compras por receta o pasillo",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary
                            )
                        }
                        Icon(
                            imageVector = Icons.Rounded.ArrowForward,
                            contentDescription = null,
                            tint = AjiAmarillo,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RegionCarouselCard(card: RegionCard, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = SurfaceContainerLow,
        modifier = Modifier.width(256.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp)
            ) {
                Image(
                    painter = painterResource(card.imageRes),
                    contentDescription = card.region.label,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                PillTag(
                    text = card.recipeCount,
                    containerColor = ScrimWarm.copy(alpha = 0.72f),
                    contentColor = SurfaceContainerLowest,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )
                PillTag(
                    text = card.region.label,
                    containerColor = regionColor(card.region),
                    contentColor = SurfaceContainerLowest,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                )
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = card.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Explorar platos",
                        style = MaterialTheme.typography.labelSmall,
                        color = AjiAmarillo,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = null,
                        tint = AjiAmarillo,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FeaturedRecipeCard(
    recipe: Recipe,
    favorite: Boolean,
    onOpen: () -> Unit,
    onFavorite: () -> Unit
) {
    Surface(
        onClick = onOpen,
        shape = RoundedCornerShape(24.dp),
        color = SurfaceContainerHigh,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(208.dp)
            ) {
                Image(
                    painter = painterResource(recipe.imageRes),
                    contentDescription = recipe.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, ScrimWarm.copy(alpha = 0.55f)),
                                startY = 140f
                            )
                        )
                )
                FavoriteCircleButton(
                    favorite = favorite,
                    onClick = onFavorite,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                )
                RatingPill(
                    rating = recipe.rating,
                    reviews = recipe.reviewCount,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                )
                PillTag(
                    text = recipe.difficulty,
                    containerColor = Terracotta,
                    contentColor = SurfaceContainerLowest,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainer)
                    .padding(14.dp)
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = EarthInk
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(10.dp))
                Surface(
                    color = SurfaceContainerLow,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MetaItem(Icons.Rounded.Schedule, "${recipe.prepMinutes} min")
                        MetaItem(Icons.Rounded.Group, "${recipe.portions} porciones")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            SpiceGauge(level = recipe.spiceLevel)
                            Text(
                                text = recipe.spiceLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary,
                                maxLines = 1
                            )
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
                PrimaryPillButton(
                    text = "Ver receta completa",
                    onClick = onOpen,
                    icon = Icons.Rounded.ArrowForward,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun CategoryGroup(
    title: String,
    icon: ImageVector,
    total: Int,
    recipes: List<Recipe>,
    onOpenRecipe: (String) -> Unit,
    onSeeAll: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AjiAmarillo,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = EarthInk,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Ver todas ($total)",
                style = MaterialTheme.typography.labelSmall,
                color = AjiAmarillo,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .clickable(onClick = onSeeAll)
                    .padding(4.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            recipes.take(3).forEach { recipe ->
                CompactRecipeCard(
                    recipe = recipe,
                    onClick = { onOpenRecipe(recipe.id) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun CulinaryTipsCard() {
    Surface(
        color = SurfaceContainerHigh,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.MenuBook,
                    contentDescription = null,
                    tint = AjiAmarillo,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Secretos de la Cocina Peruana",
                    style = MaterialTheme.typography.headlineSmall,
                    color = EarthInk
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Los tres pilares esenciales que transforman cualquier platillo casero en una experiencia gourmet criolla.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
            Spacer(Modifier.height(10.dp))
            TipCard(
                number = 1,
                title = "Pasta de Ají Amarillo Casera",
                description = "Hierve los ajíes despepitados tres veces cambiando el agua para quitar el exceso de picor y resaltar su aroma frutal antes de licuar con aceite vegetal.",
                containerColor = TerracottaFixed,
                contentColor = OnTerracottaFixed
            )
            Spacer(Modifier.height(8.dp))
            TipCard(
                number = 2,
                title = "El Sofrito Criollo Paciente",
                description = "Cebolla roja picada finísima cocinada a fuego lento hasta quedar translúcida; añade ajo y ají panca solo cuando la cebolla ya esté suave y dulce.",
                containerColor = AjiAmarilloLight,
                contentColor = OnAjiAmarilloLight
            )
            Spacer(Modifier.height(8.dp))
            TipCard(
                number = 3,
                title = "El Curado Preciso del Pescado",
                description = "No ahogues el pescado en limón con anticipación. El ceviche limeño se mezcla y se sirve de inmediato para conservar la textura firme del marisco.",
                containerColor = MaizMoradoFixed,
                contentColor = OnMaizMoradoFixed
            )
        }
    }
}
