package com.saborperu.feature.recipe

import android.content.Intent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.Forum
import androidx.compose.material.icons.rounded.Grain
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.ShoppingBasket
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material.icons.rounded.WorkspacePremium
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saborperu.data.CookingStep
import com.saborperu.data.Ingredient
import com.saborperu.data.Recipe
import com.saborperu.ui.components.MacroStat
import com.saborperu.ui.components.MetaItem
import com.saborperu.ui.components.PillTag
import com.saborperu.ui.components.PrimaryPillButton
import com.saborperu.ui.components.SpiceGauge
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.AjiAmarilloLight
import com.saborperu.ui.theme.AndeanSurface
import com.saborperu.ui.theme.EarthInk
import com.saborperu.ui.theme.Huacatay
import com.saborperu.ui.theme.OnAjiAmarilloLight
import com.saborperu.ui.theme.OnTerracottaFixed
import com.saborperu.ui.theme.OutlineVariant
import com.saborperu.ui.theme.ScrimWarm
import com.saborperu.ui.theme.StarAmber
import com.saborperu.ui.theme.SurfaceContainer
import com.saborperu.ui.theme.SurfaceContainerHigh
import com.saborperu.ui.theme.SurfaceContainerHighest
import com.saborperu.ui.theme.SurfaceContainerLow
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.Terracotta
import com.saborperu.ui.theme.TerracottaFixed
import com.saborperu.ui.theme.TextSecondary

@Composable
fun RecipeDetailScreen(
    recipeId: String,
    onBack: () -> Unit,
    onStartCooking: (Int) -> Unit,
    onOpenMarket: () -> Unit,
    viewModel: RecipeDetailViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val recipe = state.recipe
    val context = LocalContext.current
    var showTips by remember { mutableStateOf(false) }

    val shareRecipe = {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, recipe.name)
            putExtra(
                Intent.EXTRA_TEXT,
                "Te comparto esta receta de Sabor Perú: ${recipe.name} (${recipe.prepMinutes} min, ${recipe.portions} porciones). ${recipe.description}"
            )
        }
        context.startActivity(Intent.createChooser(intent, "Compartir receta"))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AndeanSurface)
    ) {
        DetailTopBar(
            favorite = state.favorite,
            onBack = onBack,
            onShare = shareRecipe,
            onToggleFavorite = viewModel::toggleFavorite
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        PillTag(
                            text = recipe.badge,
                            containerColor = TerracottaFixed,
                            contentColor = OnTerracottaFixed
                        )
                        PillTag(
                            text = recipe.region.label,
                            containerColor = SurfaceContainerHigh,
                            contentColor = TextSecondary
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(238.dp)
                            .clip(RoundedCornerShape(24.dp))
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
                                        colors = listOf(
                                            ScrimWarm.copy(alpha = 0.1f),
                                            ScrimWarm.copy(alpha = 0.82f)
                                        )
                                    )
                                )
                        )
                        PillTag(
                            text = recipe.originLine,
                            containerColor = ScrimWarm.copy(alpha = 0.6f),
                            contentColor = SurfaceContainerLowest,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp)
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(14.dp)
                        ) {
                            Text(
                                text = recipe.name,
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    fontSize = 26.sp,
                                    lineHeight = 31.sp
                                ),
                                color = SurfaceContainerLowest
                            )
                            Spacer(Modifier.height(6.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Star,
                                        contentDescription = null,
                                        tint = StarAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = String.format(java.util.Locale.US, "%.1f", recipe.rating),
                                        style = MaterialTheme.typography.labelMedium,
                                        color = SurfaceContainerLowest
                                    )
                                    Text(
                                        text = "(${recipe.reviewCount})",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = SurfaceContainerHighest
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Schedule,
                                        contentDescription = null,
                                        tint = SurfaceContainerLowest,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "${recipe.prepMinutes} min",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = SurfaceContainerLowest
                                    )
                                }
                                PillTag(
                                    text = "Dificultad: ${recipe.difficulty}",
                                    containerColor = SurfaceContainerLowest.copy(alpha = 0.2f),
                                    contentColor = SurfaceContainerLowest
                                )
                            }
                        }
                    }
                }
            }

            item {
                Surface(
                    color = SurfaceContainerLow,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MacroStat(Icons.Rounded.Bolt, "${recipe.calories}", "Calorías")
                        Box(
                            modifier = Modifier
                                .height(38.dp)
                                .width(1.dp)
                                .background(OutlineVariant)
                        )
                        MacroStat(Icons.Rounded.FitnessCenter, "${recipe.proteinGrams}g", "Proteínas")
                        Box(
                            modifier = Modifier
                                .height(38.dp)
                                .width(1.dp)
                                .background(OutlineVariant)
                        )
                        MacroStat(Icons.Rounded.Grain, "${recipe.carbsGrams}g", "Carbohidratos")
                    }
                }
            }

            item {
                Surface(
                    color = SurfaceContainerLowest,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.LocalFireDepartment,
                            contentDescription = null,
                            tint = Terracotta,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Nivel de picor",
                                style = MaterialTheme.typography.titleSmall,
                                color = EarthInk
                            )
                            Text(
                                text = recipe.spiceLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary
                            )
                        }
                        SpiceGauge(level = recipe.spiceLevel)
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Rounded.Restaurant,
                                contentDescription = null,
                                tint = AjiAmarillo,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = "Ingredientes",
                                style = MaterialTheme.typography.headlineSmall,
                                color = EarthInk
                            )
                        }
                        Text(
                            text = "Marca lo que ya tengas listo en tu mesa",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                    PortionStepper(
                        portions = state.portions,
                        onIncrease = viewModel::increasePortions,
                        onDecrease = viewModel::decreasePortions
                    )
                }
            }

            item {
                Surface(
                    color = SurfaceContainerLowest,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)) {
                        recipe.ingredients.forEachIndexed { index, ingredient ->
                            IngredientRow(
                                ingredient = ingredient,
                                recipe = recipe,
                                portions = state.portions,
                                checked = state.checkedIngredients.contains(ingredient.name),
                                onToggle = { viewModel.toggleIngredient(ingredient.name) }
                            )
                            if (index != recipe.ingredients.lastIndex) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(OutlineVariant.copy(alpha = 0.55f))
                                )
                            }
                        }
                    }
                }
            }

            item {
                Surface(
                    onClick = onOpenMarket,
                    color = AjiAmarilloLight.copy(alpha = 0.65f),
                    shape = CircleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingBasket,
                            contentDescription = null,
                            tint = AjiAmarillo,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Añadir a la lista del mercado",
                            style = MaterialTheme.typography.labelLarge,
                            color = AjiAmarillo
                        )
                    }
                }
            }

            item {
                Surface(
                    color = TerracottaFixed.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(Terracotta),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.WorkspacePremium,
                                contentDescription = null,
                                tint = SurfaceContainerLowest,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "El Secreto del Chef",
                                style = MaterialTheme.typography.titleMedium,
                                color = OnTerracottaFixed
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = "\"${recipe.chefQuote}\"",
                                style = MaterialTheme.typography.bodyMedium,
                                color = OnTerracottaFixed.copy(alpha = 0.9f),
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Instrucciones Paso a Paso",
                            style = MaterialTheme.typography.headlineSmall,
                            color = EarthInk
                        )
                        Text(
                            text = "Fases precisas para lograr el sabor perfecto",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                    PillTag(
                        text = "${recipe.steps.size} pasos",
                        containerColor = SurfaceContainerHigh,
                        contentColor = TextSecondary
                    )
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    recipe.steps.forEachIndexed { index, step ->
                        StepCard(
                            index = index,
                            step = step,
                            onClick = { onStartCooking(index) }
                        )
                        if (index != recipe.steps.lastIndex) {
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                }
            }

            item {
                Surface(
                    color = SurfaceContainerHigh,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerLowest),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Forum,
                                contentDescription = null,
                                tint = AjiAmarillo,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "¿Dudas durante la preparación?",
                                style = MaterialTheme.typography.titleMedium,
                                color = EarthInk
                            )
                            Text(
                                text = "34 cocineros comentaron esta receta hoy",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary
                            )
                        }
                        Surface(
                            onClick = { showTips = true },
                            shape = CircleShape,
                            color = SurfaceContainerLowest
                        ) {
                            Text(
                                text = "Ver tips",
                                style = MaterialTheme.typography.labelSmall,
                                color = AjiAmarillo,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }

        Surface(
            color = SurfaceContainerLowest,
            shadowElevation = 12.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = SurfaceContainerHigh,
                    shape = CircleShape
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Timer,
                            contentDescription = null,
                            tint = Terracotta,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "${recipe.prepMinutes}'",
                            style = MaterialTheme.typography.labelMedium,
                            color = EarthInk
                        )
                    }
                }
                Spacer(Modifier.width(10.dp))
                PrimaryPillButton(
                    text = "Iniciar Modo Cocina",
                    onClick = { onStartCooking(0) },
                    icon = Icons.Rounded.PlayArrow,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

    if (showTips) {
        AlertDialog(
            onDismissRequest = { showTips = false },
            title = { Text("Tips del chef", style = MaterialTheme.typography.headlineSmall) },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    recipe.steps.forEach { step ->
                        Column {
                            Text(
                                text = step.title,
                                style = MaterialTheme.typography.titleSmall,
                                color = EarthInk
                            )
                            Text(
                                text = step.chefTip,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showTips = false }) {
                    Text("Entendido", color = AjiAmarillo)
                }
            }
        )
    }
}

@Composable
private fun DetailTopBar(
    favorite: Boolean,
    onBack: () -> Unit,
    onShare: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    Surface(color = AndeanSurface) {
        Column(modifier = Modifier.statusBarsPadding()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onBack,
                    shape = CircleShape,
                    color = SurfaceContainerLow
                ) {
                    Box(
                        modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Volver",
                            tint = EarthInk,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                Spacer(Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .background(AjiAmarilloLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.RestaurantMenu,
                        contentDescription = null,
                        tint = AjiAmarillo,
                        modifier = Modifier.size(17.dp)
                    )
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Detalle de Receta",
                    style = MaterialTheme.typography.titleMedium,
                    color = EarthInk,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onShare) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "Compartir receta",
                        tint = TextSecondary
                    )
                }
                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = if (favorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = if (favorite) "Quitar de favoritos" else "Añadir a favoritos",
                        tint = if (favorite) Terracotta else TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun PortionStepper(
    portions: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Surface(
        color = SurfaceContainerHigh,
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onDecrease, modifier = Modifier.size(32.dp)) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = "Menos porciones",
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = portions.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = EarthInk
                )
                Text(
                    text = "porciones",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary
                )
            }
            IconButton(onClick = onIncrease, modifier = Modifier.size(32.dp)) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Más porciones",
                    tint = AjiAmarillo,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun IngredientRow(
    ingredient: Ingredient,
    recipe: Recipe,
    portions: Int,
    checked: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onToggle)
            .padding(vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            onClick = onToggle,
            shape = CircleShape,
            color = if (checked) Huacatay else Color.Transparent,
            border = BorderStroke(
                width = 1.4.dp,
                color = if (checked) Huacatay else OutlineVariant
            ),
            modifier = Modifier.size(24.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                if (checked) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = SurfaceContainerLowest,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        }
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = ingredient.name,
                style = MaterialTheme.typography.bodyMedium,
                color = if (checked) TextSecondary.copy(alpha = 0.6f) else EarthInk,
                textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None
            )
            if (ingredient.note != null) {
                Text(
                    text = ingredient.note,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary.copy(alpha = 0.8f)
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        Text(
            text = ingredient.amountFor(recipe.portions, portions),
            style = MaterialTheme.typography.labelLarge,
            color = if (checked) TextSecondary.copy(alpha = 0.6f) else EarthInk,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun StepCard(
    index: Int,
    step: CookingStep,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = SurfaceContainerLow,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(AjiAmarilloLight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (index + 1).toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = OnAjiAmarilloLight,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = step.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = EarthInk
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = "${step.minutes} min",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = step.description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PillTag(
                    text = step.technique,
                    containerColor = SurfaceContainerHigh,
                    contentColor = TextSecondary
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Cocinar este paso",
                    style = MaterialTheme.typography.labelSmall,
                    color = AjiAmarillo
                )
                Icon(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = null,
                    tint = AjiAmarillo,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}
