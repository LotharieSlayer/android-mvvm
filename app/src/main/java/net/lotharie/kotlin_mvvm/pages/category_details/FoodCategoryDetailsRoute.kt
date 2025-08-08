package net.lotharie.kotlin_mvvm.pages.category_details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class FoodCategoryDetailsRoute(
    val categoryId: String,
)

fun NavController.navigateToDetails(
    categoryId: String? = null,
    navOptions: NavOptions? = null,
) {
    navigate(route = FoodCategoryDetailsRoute(categoryId), navOptions)
}
