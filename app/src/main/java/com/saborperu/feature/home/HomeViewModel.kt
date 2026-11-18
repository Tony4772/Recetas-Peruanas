package com.saborperu.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saborperu.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val query: String = "",
    val selectedFilter: Int = 0,
    val favorites: Set<String> = emptySet()
)

class HomeViewModel : ViewModel() {

    private val query = MutableStateFlow("")
    private val selectedFilter = MutableStateFlow(0)

    val uiState: StateFlow<HomeUiState> = combine(
        query,
        selectedFilter,
        RecipeRepository.favorites
    ) { text, filter, favorites ->
        HomeUiState(query = text, selectedFilter = filter, favorites = favorites)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState(favorites = RecipeRepository.favorites.value)
    )

    fun onQueryChange(value: String) {
        query.value = value
    }

    fun onFilterSelected(index: Int) {
        selectedFilter.value = index
    }

    fun toggleFavorite(recipeId: String) {
        RecipeRepository.toggleFavorite(recipeId)
    }
}
