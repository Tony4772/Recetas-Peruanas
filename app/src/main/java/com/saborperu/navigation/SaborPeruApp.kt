package com.saborperu.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saborperu.feature.cooking.CookingModeScreen
import com.saborperu.feature.favorites.FavoritesScreen
import com.saborperu.feature.favorites.PlannerScreen
import com.saborperu.feature.home.HomeScreen
import com.saborperu.feature.market.MarketListScreen
import com.saborperu.feature.recipe.RecipeDetailScreen
import com.saborperu.feature.regions.RegionExplorerScreen
import com.saborperu.ui.theme.AjiAmarillo
import com.saborperu.ui.theme.AndeanSurface
import com.saborperu.ui.theme.SurfaceContainerLowest
import com.saborperu.ui.theme.TextSecondary

private data class BottomTab(
    val label: String,
    val icon: ImageVector,
    val route: String
)

private val bottomTabs = listOf(
    BottomTab("Inicio", Icons.Rounded.Home, Destination.Home.route),
    BottomTab("Regiones", Icons.Rounded.Explore, Destination.Regions.createRoute("Costa")),
    BottomTab("Favoritos", Icons.Rounded.Favorite, Destination.Favorites.route),
    BottomTab("Planificador", Icons.Rounded.CalendarMonth, Destination.Planner.route)
)

@Composable
fun SaborPeruApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = currentRoute in setOf(
        Destination.Home.route,
        Destination.Regions.route,
        Destination.MarketList.route,
        Destination.Favorites.route,
        Destination.Planner.route
    )

    Scaffold(
        containerColor = AndeanSurface,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                SaborPeruBottomBar(
                    currentRoute = currentRoute,
                    onNavigate = { route -> navController.navigateTopLevel(route) }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Destination.Home.route) {
                HomeScreen(
                    onOpenRecipe = { recipeId ->
                        navController.navigate(Destination.RecipeDetail.createRoute(recipeId))
                    },
                    onOpenRegion = { region ->
                        navController.navigateTopLevel(Destination.Regions.createRoute(region))
                    },
                    onOpenMarket = { navController.navigate(Destination.MarketList.route) },
                    onOpenFavorites = {
                        navController.navigateTopLevel(Destination.Favorites.route)
                    }
                )
            }
            composable(
                route = Destination.Regions.route,
                arguments = listOf(navArgument("region") {
                    type = NavType.StringType
                    defaultValue = "Costa"
                })
            ) { entry ->
                RegionExplorerScreen(
                    initialRegion = entry.arguments?.getString("region") ?: "Costa",
                    onOpenRecipe = { recipeId ->
                        navController.navigate(Destination.RecipeDetail.createRoute(recipeId))
                    },
                    onOpenFavorites = {
                        navController.navigateTopLevel(Destination.Favorites.route)
                    }
                )
            }
            composable(
                route = Destination.RecipeDetail.route,
                arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
            ) { entry ->
                val recipeId = entry.arguments?.getString("recipeId").orEmpty()
                RecipeDetailScreen(
                    recipeId = recipeId,
                    onBack = { navController.popBackStack() },
                    onStartCooking = { stepIndex ->
                        navController.navigate(Destination.CookingMode.createRoute(recipeId, stepIndex))
                    },
                    onOpenMarket = { navController.navigate(Destination.MarketList.route) }
                )
            }
            composable(
                route = Destination.CookingMode.route,
                arguments = listOf(
                    navArgument("recipeId") { type = NavType.StringType },
                    navArgument("stepIndex") {
                        type = NavType.IntType
                        defaultValue = 0
                    }
                )
            ) { entry ->
                val recipeId = entry.arguments?.getString("recipeId").orEmpty()
                val stepIndex = entry.arguments?.getInt("stepIndex") ?: 0
                CookingModeScreen(
                    recipeId = recipeId,
                    initialStepIndex = stepIndex,
                    onClose = { navController.popBackStack() },
                    onFinish = {
                        navController.navigate(Destination.RecipeDetail.createRoute(recipeId)) {
                            popUpTo(Destination.RecipeDetail.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Destination.MarketList.route) {
                MarketListScreen(
                    onOpenRecipe = { recipeId ->
                        navController.navigate(Destination.RecipeDetail.createRoute(recipeId))
                    }
                )
            }
            composable(Destination.Favorites.route) {
                FavoritesScreen(
                    onOpenRecipe = { recipeId ->
                        navController.navigate(Destination.RecipeDetail.createRoute(recipeId))
                    }
                )
            }
            composable(Destination.Planner.route) {
                PlannerScreen(
                    onOpenRecipe = { recipeId ->
                        navController.navigate(Destination.RecipeDetail.createRoute(recipeId))
                    }
                )
            }
        }
    }
}

private fun NavHostController.navigateTopLevel(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = route != Destination.Home.route
    }
}

@Composable
private fun SaborPeruBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    Surface(
        color = SurfaceContainerLowest,
        shadowElevation = 12.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(64.dp)
                .padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomTabs.forEach { tab ->
                val selected = when (tab.route) {
                    Destination.Home.route -> currentRoute == Destination.Home.route
                    Destination.Favorites.route -> currentRoute == Destination.Favorites.route
                    Destination.Planner.route -> currentRoute == Destination.Planner.route
                    else -> currentRoute?.startsWith("regions") == true
                }
                val tint = if (selected) AjiAmarillo else TextSecondary
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onNavigate(tab.route) }
                        .padding(vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        tint = tint,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = tint
                    )
                }
            }
        }
    }
}
