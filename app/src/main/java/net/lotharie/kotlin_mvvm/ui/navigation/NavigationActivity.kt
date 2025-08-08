package net.lotharie.kotlin_mvvm.ui.navigation

import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy.Builder
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
import dagger.hilt.android.AndroidEntryPoint
import net.lotharie.kotlin_mvvm.pages.categories.FoodCategoriesBaseRoute
import net.lotharie.kotlin_mvvm.pages.categories.FoodCategoriesScreen
import net.lotharie.kotlin_mvvm.pages.categories.FoodCategoriesViewModel
import net.lotharie.kotlin_mvvm.pages.category_details.FoodCategoryDetailsScreen
import net.lotharie.kotlin_mvvm.pages.category_details.FoodCategoryDetailsViewModel
import net.lotharie.kotlin_mvvm.ui.navigation.NavigationKeys.Arg.FOOD_CATEGORY_ID
import net.lotharie.kotlin_mvvm.ui.theme.KotlinMVVMTheme


// Single Activity per app
@AndroidEntryPoint
class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KotlinMVVMTheme {
                FoodApp()
            }
        }
    }
}