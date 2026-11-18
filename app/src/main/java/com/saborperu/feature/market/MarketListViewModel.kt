package com.saborperu.feature.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saborperu.data.MarketItem
import com.saborperu.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class MarketUiState(
    val items: List<MarketItem> = emptyList(),
    val groupByAisle: Boolean = false
) {
    val totalCount: Int get() = items.size
    val pendingCount: Int get() = items.count { !it.checked }
    val completedCount: Int get() = items.count { it.checked }
    val completionPercent: Int
        get() = if (items.isEmpty()) 0 else (completedCount * 100f / items.size).toInt()
    val estimatedPrice: Double get() = items.sumOf { it.estimatedPrice }
    val estimatedWeight: Double get() = items.sumOf { it.weightKg }
}

class MarketListViewModel : ViewModel() {

    private val groupByAisle = MutableStateFlow(false)

    val uiState: StateFlow<MarketUiState> = combine(
        RecipeRepository.marketItems,
        groupByAisle
    ) { items, byAisle ->
        MarketUiState(items = items, groupByAisle = byAisle)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MarketUiState(items = RecipeRepository.marketItems.value)
    )

    fun setGrouping(byAisle: Boolean) {
        groupByAisle.value = byAisle
    }

    fun toggleGrouping() {
        groupByAisle.value = !groupByAisle.value
    }

    fun toggleItem(itemId: String) {
        RecipeRepository.toggleMarketItem(itemId)
    }

    fun clearCompleted() {
        RecipeRepository.clearCompleted()
    }

    fun addItem(label: String, aisle: String) {
        val cleanLabel = label.trim()
        if (cleanLabel.isEmpty()) return
        RecipeRepository.addMarketItem(
            label = cleanLabel,
            aisle = aisle.trim().ifEmpty { "Abarrotes" }
        )
    }
}
