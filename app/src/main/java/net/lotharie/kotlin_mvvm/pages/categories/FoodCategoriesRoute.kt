package net.lotharie.kotlin_mvvm.pages.categories

import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import kotlinx.serialization.Serializable

@Serializable object FoodCategoriesRoute // route to food categories screen

@Serializable data object FoodCategoriesBaseRoute // route to base navigation graph

fun NavController.navigateToFoodCategories(navOptions: NavOptions) =
    navigate(route = FoodCategoriesRoute, navOptions)

fun NavGraphBuilder.foodCategoriesScreen(
    onDetailsClick: (String) -> Unit,
    categoryDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<FoodCategoriesBaseRoute>(startDestination = FoodCategoriesRoute) {
        composable<FoodCategoriesRoute> {
            FoodCategoriesScreen(onDetailsClick)
        }
        categoryDestination()
    }
}
