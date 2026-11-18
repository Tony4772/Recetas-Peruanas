package com.saborperu.navigation

sealed class Destination(val route: String) {
    data object Home : Destination("home")
    data object Regions : Destination("regions?region={region}") {
        fun createRoute(region: String = "Costa") = "regions?region=$region"
    }

    data object RecipeDetail : Destination("recipe/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe/$recipeId"
    }

    data object CookingMode : Destination("cook/{recipeId}/{stepIndex}") {
        fun createRoute(recipeId: String, stepIndex: Int = 0) = "cook/$recipeId/$stepIndex"
    }

    data object MarketList : Destination("market")
    data object Favorites : Destination("favorites")
    data object Planner : Destination("planner")
}
