package com.andeanculinary.app.model

enum class Region(val displayName: String) {
    COSTA("Costa"),
    SIERRA("Sierra"),
    SELVA("Selva")
}

enum class Category(val displayName: String) {
    ALL("Todas"),
    CEVICHES("Ceviches & Tiraditos"),
    STIR_FRIES("Salteados al Wok"),
    STEWS("Guisos & Secos"),
    APPETIZERS("Entradas & Causas"),
    SOUPS("Chupes & Sopas"),
    DESSERTS("Postres Criollos"),
    BEVERAGES("Chicha & Cócteles")
}

data class Ingredient(
    val id: String,
    val name: String,
    val baseAmount: Double,
    val unit: String,
    val notes: String = "",
    val category: String = "general"
)

data class CookingStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val chefSecret: String? = null,
    val timerDurationSeconds: Int? = null,
    val timerLabel: String? = null
)

data class Pairing(
    val drink: String,
    val description: String
)

data class NutritionalInfo(
    val calories: Int,
    val proteins: Int,
    val carbohydrates: Int,
    val fats: Int = 0
)

data class Recipe(
    val id: String,
    val title: String,
    val quechuaOrLocalName: String? = null,
    val subtitle: String,
    val description: String,
    val region: Region,
    val category: Category,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val difficulty: String,
    val spiceLevel: Int, // 0 to 5
    val defaultServings: Int = 4,
    val imageUrl: String,
    val tags: List<String> = emptyList(),
    val nutrition: NutritionalInfo = NutritionalInfo(400, 30, 40),
    val pairing: Pairing,
    val culturalHistory: String,
    val ingredients: List<Ingredient>,
    val steps: List<CookingStep>
)

data class ChiliInfo(
    val id: String,
    val name: String,
    val scientificName: String,
    val scoville: String,
    val heatLevel: Int,
    val origin: String,
    val flavorProfile: String,
    val culinaryUse: String,
    val substitution: String
)

data class PantryItem(
    val id: String,
    val name: String,
    val localName: String,
    val category: String,
    val description: String,
    val vitalFor: List<String>,
    val tip: String
)
