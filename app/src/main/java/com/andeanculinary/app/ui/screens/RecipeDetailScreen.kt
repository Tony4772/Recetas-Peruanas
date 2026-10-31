package com.andeanculinary.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.WineBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.andeanculinary.app.model.Recipe
import com.andeanculinary.app.ui.components.AjiHeatGauge
import com.andeanculinary.app.ui.components.QuantityStepper
import com.andeanculinary.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    isSaved: Boolean,
    onBack: () -> Unit,
    onToggleSave: () -> Unit,
    onStartCooking: () -> Unit,
    modifier: Modifier = Modifier
) {
    var servings by remember { mutableIntStateOf(recipe.defaultServings) }
    var checkedIngredients by remember { mutableStateOf(setOf<String>()) }

    val scaleRatio = servings.toFloat() / recipe.defaultServings.toFloat()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.title, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = onToggleSave) {
                        Icon(
                            imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Favorito",
                            tint = if (isSaved) AnticuchoRustSecondary else OnSurfaceVariantText
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceBase)
            )
        },
        bottomBar = {
            Surface(
                color = SurfaceBase,
                tonalElevation = 6.dp,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onStartCooking,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = AjiGoldPrimary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(52.dp)
                ) {
                    Icon(Icons.Default.Restaurant, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Empezar a Cocinar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        containerColor = SurfaceBase,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding() + 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Hero Image
            item {
                Card(
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 10f)
                ) {
                    AsyncImage(
                        model = recipe.imageUrl,
                        contentDescription = recipe.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // Title & Lore
            item {
                Column {
                    if (recipe.quechuaOrLocalName != null) {
                        Text(
                            text = recipe.quechuaOrLocalName.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = AjiGoldPrimaryDark,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = recipe.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = recipe.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    AjiHeatGauge(level = recipe.spiceLevel)

                    Spacer(modifier = Modifier.height(14.dp))

                    // Nutritional Breakdown per serving
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerHigh),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "INFORMACIÓN NUTRICIONAL",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = AnticuchoRustSecondary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Por porción",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // Calories
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = SurfaceBase,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Text("Calorías", style = MaterialTheme.typography.labelSmall, color = OutlineWarm)
                                        Text("${recipe.nutrition.calories}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                        Text("kcal", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }

                                // Proteins
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = SurfaceBase,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Text("Proteínas", style = MaterialTheme.typography.labelSmall, color = OutlineWarm)
                                        Text("${recipe.nutrition.proteins}g", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = HuacatayHerbGreen)
                                        Text("proteína", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }

                                // Carbohydrates
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = SurfaceBase,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Text("Carbohidratos", style = MaterialTheme.typography.labelSmall, color = OutlineWarm)
                                        Text("${recipe.nutrition.carbohydrates}g", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = AjiGoldPrimaryDark)
                                        Text("carbos", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Ingredients Header & Stepper
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Ingredientes",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${checkedIngredients.size} de ${recipe.ingredients.size} listos",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    QuantityStepper(
                        servings = servings,
                        onServingsChange = { servings = it }
                    )
                }
            }

            // Ingredients items with 24dp circular checkbox
            items(recipe.ingredients, key = { it.id }) { ing ->
                val isChecked = checkedIngredients.contains(ing.id)
                val scaledAmount = (ing.baseAmount * scaleRatio * 10).toInt() / 10.0

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            checkedIngredients = if (isChecked) {
                                checkedIngredients - ing.id
                            } else {
                                checkedIngredients + ing.id
                            }
                        }
                        .background(if (isChecked) SurfaceContainerHighest.copy(alpha = 0.5f) else SurfaceContainer)
                        .padding(12.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isChecked) HuacatayHerbGreen else Color.Transparent)
                            .border(
                                width = 2.dp,
                                color = if (isChecked) HuacatayHerbGreen else OutlineWarm,
                                shape = CircleShape
                            )
                    ) {
                        if (isChecked) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = ing.name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None,
                            color = if (isChecked) OutlineWarm else MaterialTheme.colorScheme.onSurface
                        )
                        if (ing.notes.isNotEmpty()) {
                            Text(
                                text = ing.notes,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Text(
                        text = "$scaledAmount ${ing.unit}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = AjiGoldPrimaryDark
                    )
                }
            }

            // Beverage Pairing
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaizMoradoTertiary.copy(alpha = 0.08f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.WineBar,
                            contentDescription = null,
                            tint = MaizMoradoTertiary
                        )
                        Column {
                            Text(
                                text = "Maridaje Sugerido",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaizMoradoTertiary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = recipe.pairing.drink,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = recipe.pairing.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
