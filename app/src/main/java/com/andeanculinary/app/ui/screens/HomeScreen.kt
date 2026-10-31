package com.andeanculinary.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.andeanculinary.app.data.RecipeRepository
import com.andeanculinary.app.model.Category
import com.andeanculinary.app.model.Recipe
import com.andeanculinary.app.model.Region
import com.andeanculinary.app.ui.components.RecipeCard
import com.andeanculinary.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onRecipeClick: (Recipe) -> Unit,
    onStartCooking: (Recipe) -> Unit,
    savedRecipeIds: Set<String>,
    onToggleSave: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(Category.ALL) }
    var selectedRegion by remember { mutableStateOf<Region?>(null) }

    val allRecipes = RecipeRepository.recipes
    val filteredRecipes = remember(searchQuery, selectedCategory, selectedRegion) {
        allRecipes.filter { recipe ->
            val matchesQuery = searchQuery.isEmpty() ||
                    recipe.title.contains(searchQuery, ignoreCase = true) ||
                    recipe.description.contains(searchQuery, ignoreCase = true)
            val matchesCategory = selectedCategory == Category.ALL || recipe.category == selectedCategory
            val matchesRegion = selectedRegion == null || recipe.region == selectedRegion
            matchesQuery && matchesCategory && matchesRegion
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(AjiGoldPrimary)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = "Andean Culinary",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceBase
                )
            )
        },
        containerColor = SurfaceBase,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding() + 80.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Search Bar
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar ceviche, lomo, ají amarillo...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = OutlineWarm
                        )
                    },
                    shape = CircleShape,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = SurfaceContainerLow,
                        unfocusedContainerColor = SurfaceContainerLow,
                        focusedBorderColor = AjiGoldPrimary,
                        unfocusedBorderColor = OutlineVariantWarm
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }

            // Region filter row
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    FilterChip(
                        selected = selectedRegion == null,
                        onClick = { selectedRegion = null },
                        label = { Text("Todas las Regiones") },
                        shape = CircleShape,
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AjiGoldPrimaryDark,
                            selectedLabelColor = Color.White
                        )
                    )
                    Region.entries.forEach { region ->
                        FilterChip(
                            selected = selectedRegion == region,
                            onClick = { selectedRegion = if (selectedRegion == region) null else region },
                            label = { Text(region.displayName) },
                            shape = CircleShape,
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AjiGoldPrimaryDark,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            // Category filter row
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    Category.entries.forEach { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat.displayName) },
                            shape = CircleShape,
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AnticuchoRustSecondary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            // Recipe List
            items(filteredRecipes, key = { it.id }) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    isSaved = savedRecipeIds.contains(recipe.id),
                    onSelect = { onRecipeClick(recipe) },
                    onToggleSave = { onToggleSave(recipe.id) },
                    onStartCooking = { onStartCooking(recipe) }
                )
            }
        }
    }
}
