package com.andeanculinary.app.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andeanculinary.app.data.RecipeRepository
import com.andeanculinary.app.model.Recipe
import com.andeanculinary.app.ui.components.KitchenTimerPill
import com.andeanculinary.app.ui.screens.CookingModeScreen
import com.andeanculinary.app.ui.screens.HomeScreen
import com.andeanculinary.app.ui.screens.MercadoScreen
import com.andeanculinary.app.ui.screens.RecipeDetailScreen
import com.andeanculinary.app.ui.theme.AjiGoldPrimary
import com.andeanculinary.app.ui.theme.SurfaceBase

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    data object Home : Screen("home", "Recetas", Icons.Default.Restaurant)
    data object Mercado : Screen("mercado", "Mercado", Icons.Default.LocalFireDepartment)
    data object Saved : Screen("saved", "Guardados", Icons.Default.Bookmark)
    data object Detail : Screen("detail/{recipeId}", "Detalle") {
        fun createRoute(recipeId: String) = "detail/$recipeId"
    }
    data object CookingMode : Screen("cooking/{recipeId}", "Cocinando") {
        fun createRoute(recipeId: String) = "cooking/$recipeId"
    }
}

val BottomNavItems = listOf(
    Screen.Home,
    Screen.Mercado,
    Screen.Saved
)

@Composable
fun AndeanNavHost(
    navController: NavHostController = rememberNavController()
) {
    var savedRecipeIds by remember {
        mutableStateOf(setOf("ceviche-clasico-corvina", "lomo-saltado-criollo"))
    }

    // Active Timer State
    var timerSeconds by remember { mutableIntStateOf(0) }
    var timerRunning by remember { mutableStateOf(false) }
    var timerLabel by remember { mutableStateOf<String?>(null) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in BottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(containerColor = SurfaceBase) {
                    BottomNavItems.forEach { item ->
                        val selected = currentRoute == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(Screen.Home.route) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = { item.icon?.let { Icon(it, contentDescription = item.title) } },
                            label = { Text(item.title) },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = AjiGoldPrimary.copy(alpha = 0.2f),
                                selectedIconColor = AjiGoldPrimary
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(
                        onRecipeClick = { recipe ->
                            navController.navigate(Screen.Detail.createRoute(recipe.id))
                        },
                        onStartCooking = { recipe ->
                            navController.navigate(Screen.CookingMode.createRoute(recipe.id))
                        },
                        savedRecipeIds = savedRecipeIds,
                        onToggleSave = { id ->
                            savedRecipeIds = if (savedRecipeIds.contains(id)) {
                                savedRecipeIds - id
                            } else {
                                savedRecipeIds + id
                            }
                        }
                    )
                }

                composable(Screen.Mercado.route) {
                    MercadoScreen()
                }

                composable(Screen.Saved.route) {
                    val savedRecipes = RecipeRepository.recipes.filter { savedRecipeIds.contains(it.id) }
                    HomeScreen(
                        onRecipeClick = { recipe ->
                            navController.navigate(Screen.Detail.createRoute(recipe.id))
                        },
                        onStartCooking = { recipe ->
                            navController.navigate(Screen.CookingMode.createRoute(recipe.id))
                        },
                        savedRecipeIds = savedRecipeIds,
                        onToggleSave = { id ->
                            savedRecipeIds = savedRecipeIds - id
                        }
                    )
                }

                composable(Screen.Detail.route) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getString("recipeId")
                    val recipe = RecipeRepository.getRecipeById(recipeId ?: "")
                    if (recipe != null) {
                        RecipeDetailScreen(
                            recipe = recipe,
                            isSaved = savedRecipeIds.contains(recipe.id),
                            onBack = { navController.popBackStack() },
                            onToggleSave = {
                                savedRecipeIds = if (savedRecipeIds.contains(recipe.id)) {
                                    savedRecipeIds - recipe.id
                                } else {
                                    savedRecipeIds + recipe.id
                                }
                            },
                            onStartCooking = {
                                navController.navigate(Screen.CookingMode.createRoute(recipe.id))
                            }
                        )
                    }
                }

                composable(Screen.CookingMode.route) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getString("recipeId")
                    val recipe = RecipeRepository.getRecipeById(recipeId ?: "")
                    if (recipe != null) {
                        CookingModeScreen(
                            recipe = recipe,
                            onExit = { navController.popBackStack() }
                        )
                    }
                }
            }

            // Global floating kitchen timer
            if (timerLabel != null) {
                KitchenTimerPill(
                    label = timerLabel ?: "Temporizador",
                    totalSeconds = 300,
                    remainingSeconds = timerSeconds,
                    isRunning = timerRunning,
                    onToggleRunning = { timerRunning = !timerRunning },
                    onClose = { timerLabel = null },
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }
    }
}
