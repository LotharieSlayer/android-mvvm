package net.lotharie.kotlin_mvvm.ui.navigation

import net.lotharie.kotlin_mvvm.pages.categories.FoodCategoriesBaseRoute
import net.lotharie.kotlin_mvvm.pages.categories.foodCategoriesScreen
import net.lotharie.kotlin_mvvm.pages.category_details.foodCategoryDetailsScreen
import net.lotharie.kotlin_mvvm.pages.category_details.navigateToDetails
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost

@Composable
fun NavigationHost(
    appState: NavigationAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = FoodCategoriesBaseRoute,
        modifier = modifier,
    ) {
        foodCategoriesScreen(
            onDetailsClick = navController::navigateToDetails,
        ) {
            foodCategoryDetailsScreen(
            )
        }
    }
}
