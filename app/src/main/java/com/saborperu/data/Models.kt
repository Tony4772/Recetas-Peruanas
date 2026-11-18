package com.saborperu.data

import java.util.Locale
import kotlin.math.roundToInt

enum class Region(val label: String) {
    COSTA("Costa"),
    SIERRA("Sierra"),
    SELVA("Selva")
}

enum class RecipeCategory(val label: String) {
    FONDO("Platos de fondo"),
    BEBIDA("Bebidas"),
    POSTRE("Postres")
}

data class Ingredient(
    val name: String,
    val quantity: Double?,
    val unit: String,
    val aisle: String,
    val note: String? = null
) {
    fun amountFor(basePortions: Int, portions: Int): String {
        if (quantity == null) return unit
        val scaled = quantity * portions / basePortions
        val rounded = (scaled * 100).roundToInt() / 100.0
        val number = if (rounded % 1.0 == 0.0) {
            rounded.toInt().toString()
        } else {
            String.format(Locale.US, "%.2f", rounded).trimEnd('0').trimEnd('.')
        }
        return "$number $unit".trim()
    }
}

data class StepIngredient(
    val name: String,
    val detail: String
)

data class CookingStep(
    val title: String,
    val description: String,
    val minutes: Int,
    val technique: String,
    val fireLabel: String,
    val chefTip: String,
    val timerSeconds: Int,
    val ingredients: List<StepIngredient>
)

data class Recipe(
    val id: String,
    val name: String,
    val region: Region,
    val category: RecipeCategory,
    val originLine: String,
    val description: String,
    val imageRes: Int,
    val prepMinutes: Int,
    val portions: Int,
    val difficulty: String,
    val spiceLevel: Int,
    val spiceLabel: String,
    val rating: Double,
    val reviewCount: Int,
    val calories: Int,
    val proteinGrams: Int,
    val carbsGrams: Int,
    val badge: String,
    val regionTag: String,
    val tags: List<String>,
    val ingredients: List<Ingredient>,
    val steps: List<CookingStep>,
    val chefQuote: String
)

data class MarketItem(
    val id: String,
    val recipeId: String,
    val recipeName: String,
    val recipeImageRes: Int,
    val label: String,
    val aisle: String,
    val note: String?,
    val checked: Boolean,
    val estimatedPrice: Double = 0.0,
    val weightKg: Double = 0.0
)
