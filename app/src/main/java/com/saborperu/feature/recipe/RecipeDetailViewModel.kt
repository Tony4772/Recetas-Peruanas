package com.saborperu.feature.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saborperu.data.Recipe
import com.saborperu.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class RecipeDetailUiState(
    val recipe: Recipe,
    val portions: Int,
    val checkedIngredients: Set<String>,
    val favorite: Boolean
)

class RecipeDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val recipeId: String = savedStateHandle.get<String>("recipeId").orEmpty()
    val recipe: Recipe = RecipeRepository.recipeById(recipeId) ?: RecipeRepository.recipes.first()

    private val portions = MutableStateFlow(recipe.portions)
    private val checkedIngredients = MutableStateFlow<Set<String>>(emptySet())

    val uiState: StateFlow<RecipeDetailUiState> = combine(
        portions,
        checkedIngredients,
        RecipeRepository.favorites
    ) { currentPortions, checked, favorites ->
        RecipeDetailUiState(
            recipe = recipe,
            portions = currentPortions,
            checkedIngredients = checked,
            favorite = favorites.contains(recipe.id)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RecipeDetailUiState(
            recipe = recipe,
            portions = recipe.portions,
            checkedIngredients = emptySet(),
            favorite = RecipeRepository.favorites.value.contains(recipe.id)
        )
    )

    fun increasePortions() {
        portions.value = (portions.value + 1).coerceAtMost(12)
    }

    fun decreasePortions() {
        portions.value = (portions.value - 1).coerceAtLeast(1)
    }

    fun toggleIngredient(name: String) {
        checkedIngredients.value = checkedIngredients.value.toMutableSet().apply {
            if (!add(name)) remove(name)
        }
    }

    fun toggleFavorite() {
        RecipeRepository.toggleFavorite(recipe.id)
    }
}
