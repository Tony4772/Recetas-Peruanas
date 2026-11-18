package com.saborperu.feature.regions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saborperu.data.Recipe
import com.saborperu.data.RecipeCategory
import com.saborperu.data.RecipeRepository
import com.saborperu.data.Region
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

enum class SortMode(val label: String) {
    POPULAR("Más populares"),
    QUICK("Menos tiempo de preparación"),
    TOP_RATED("Mayor calificación")
}

data class RegionUiState(
    val region: Region = Region.COSTA,
    val selectedCategory: Int = 0,
    val sortMode: SortMode = SortMode.POPULAR,
    val query: String = "",
    val favorites: Set<String> = emptySet(),
    val recipes: List<Recipe> = emptyList()
)

class RegionExplorerViewModel(initialRegion: String) : ViewModel() {

    private val region = MutableStateFlow(
        Region.entries.firstOrNull { it.label.equals(initialRegion, ignoreCase = true) } ?: Region.COSTA
    )
    private val selectedCategory = MutableStateFlow(0)
    private val sortMode = MutableStateFlow(SortMode.POPULAR)
    private val query = MutableStateFlow("")

    val uiState: StateFlow<RegionUiState> = combine(
        region,
        selectedCategory,
        sortMode,
        query,
        RecipeRepository.favorites
    ) { currentRegion, categoryIndex, sort, text, favorites ->
        val category = when (categoryIndex) {
            1 -> RecipeCategory.FONDO
            2 -> RecipeCategory.BEBIDA
            3 -> RecipeCategory.POSTRE
            else -> null
        }
        val cleaned = text.trim()
        val filtered = RecipeRepository.recipesByRegion(currentRegion)
            .filter { recipe -> category == null || recipe.category == category }
            .filter { recipe ->
                cleaned.isBlank() ||
                    recipe.name.contains(cleaned, ignoreCase = true) ||
                    recipe.description.contains(cleaned, ignoreCase = true) ||
                    recipe.tags.any { it.contains(cleaned, ignoreCase = true) }
            }
        val sorted = when (sort) {
            SortMode.POPULAR -> filtered.sortedByDescending { it.reviewCount }
            SortMode.QUICK -> filtered.sortedBy { it.prepMinutes }
            SortMode.TOP_RATED -> filtered.sortedByDescending { it.rating }
        }
        RegionUiState(
            region = currentRegion,
            selectedCategory = categoryIndex,
            sortMode = sort,
            query = text,
            favorites = favorites,
            recipes = sorted
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RegionUiState(
            region = region.value,
            recipes = RecipeRepository.recipesByRegion(region.value),
            favorites = RecipeRepository.favorites.value
        )
    )

    fun onRegionSelected(newRegion: Region) {
        region.value = newRegion
    }

    fun onCategorySelected(index: Int) {
        selectedCategory.value = index
    }

    fun onSortSelected(mode: SortMode) {
        sortMode.value = mode
    }

    fun onQueryChange(value: String) {
        query.value = value
    }

    fun toggleFavorite(recipeId: String) {
        RecipeRepository.toggleFavorite(recipeId)
    }
}
