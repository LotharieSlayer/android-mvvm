package net.lotharie.kotlin_mvvm.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import net.lotharie.kotlin_mvvm.pages.categories.FoodCategoriesScreen
import net.lotharie.kotlin_mvvm.pages.categories.FoodCategoriesViewModel
import net.lotharie.kotlin_mvvm.pages.category_details.FoodCategoryDetailsScreen
import net.lotharie.kotlin_mvvm.pages.category_details.FoodCategoryDetailsViewModel
import net.lotharie.kotlin_mvvm.ui.NavigationKeys.Arg.FOOD_CATEGORY_ID
import net.lotharie.kotlin_mvvm.ui.theme.KotlinMVVMTheme


// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinMVVMTheme {
                FoodApp()
            }
        }
    }

}

@Composable
private fun FoodApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {
        composable(route = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {
            FoodCategoriesDestination(navController)
        }
        composable(
            route = NavigationKeys.Route.FOOD_CATEGORY_DETAILS,
            arguments = listOf(navArgument(FOOD_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            FoodCategoryDetailsDestination()
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun FoodCategoriesDestination(
    navController: NavHostController,
    viewModel: FoodCategoriesViewModel = hiltViewModel()
) {
    val state by viewModel.categoriesUiState.collectAsStateWithLifecycle()
    FoodCategoriesScreen(
        state = state,
        onNavigationRequested = { itemId ->
            navController.navigate("${NavigationKeys.Route.FOOD_CATEGORIES_LIST}/${itemId}")
        })
}

@Composable
private fun FoodCategoryDetailsDestination(
    viewModel: FoodCategoryDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.categoryDetailsUiState.collectAsStateWithLifecycle()
    FoodCategoryDetailsScreen(state)
}

object NavigationKeys {

    object Arg {
        const val FOOD_CATEGORY_ID = "foodCategoryName"
    }

    object Route {
        const val FOOD_CATEGORIES_LIST = "food_categories_list"
        const val FOOD_CATEGORY_DETAILS = "$FOOD_CATEGORIES_LIST/{$FOOD_CATEGORY_ID}"
    }

}