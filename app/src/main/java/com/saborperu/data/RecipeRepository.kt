package com.saborperu.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object RecipeRepository {

    val recipes: List<Recipe> = RecetasData.recipes

    private val _favorites = MutableStateFlow(setOf("ceviche-limeno"))
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    private val _marketItems = MutableStateFlow(RecetasData.marketSeed)
    val marketItems: StateFlow<List<MarketItem>> = _marketItems.asStateFlow()

    fun recipeById(id: String): Recipe? = recipes.firstOrNull { it.id == id }

    fun recipesByRegion(region: Region): List<Recipe> = recipes.filter { it.region == region }

    fun favoritesList(): List<Recipe> = recipes.filter { _favorites.value.contains(it.id) }

    fun popularBeverages(): List<Recipe> = listOfNotNull(
        recipeById("pisco-sour"),
        recipeById("chicha-morada"),
        recipeById("emoliente")
    )

    fun popularDesserts(): List<Recipe> = listOfNotNull(
        recipeById("suspiro-limena"),
        recipeById("picarones"),
        recipeById("queso-helado")
    )

    fun toggleFavorite(recipeId: String) {
        _favorites.update { current ->
            if (current.contains(recipeId)) current - recipeId else current + recipeId
        }
    }

    fun toggleMarketItem(itemId: String) {
        _marketItems.update { items ->
            items.map { item ->
                if (item.id == itemId) item.copy(checked = !item.checked) else item
            }
        }
    }

    fun clearCompleted() {
        _marketItems.update { items -> items.filterNot { it.checked } }
    }

    fun addMarketItem(label: String, aisle: String) {
        val item = MarketItem(
            id = "manual-${System.currentTimeMillis()}",
            recipeId = "manual",
            recipeName = "Añadido manual",
            recipeImageRes = 0,
            label = label,
            aisle = aisle,
            note = "Manual",
            checked = false
        )
        _marketItems.update { items -> items + item }
    }
}
