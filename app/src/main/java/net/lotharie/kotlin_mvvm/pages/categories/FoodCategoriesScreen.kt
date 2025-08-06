package net.lotharie.kotlin_mvvm.pages.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.annotation.ExperimentalCoilApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import net.lotharie.kotlin_mvvm.ui.components.atoms.LoadingCircleBox
import net.lotharie.kotlin_mvvm.ui.components.organisms.appbar.CategoriesAppBar
import net.lotharie.kotlin_mvvm.ui.components.organisms.food.FoodItemList
import net.lotharie.kotlin_mvvm.ui.theme.KotlinMVVMTheme

@ExperimentalCoilApi
@Composable
fun FoodCategoriesScreen(
    state: FoodCategoriesContract.State,
    effectFlow: Flow<FoodCategoriesContract.Effect>?,
    onNavigationRequested: (itemId: String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Listen for side effects from the VM
    LaunchedEffect(effectFlow) {
        effectFlow?.onEach { effect ->
            if (effect is FoodCategoriesContract.Effect.DataWasLoaded)
                snackbarHostState.showSnackbar(
                    message = "Food categories are loaded.",
                    duration = SnackbarDuration.Short
                )
        }?.collect()
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { CategoriesAppBar() }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            FoodItemList(foodItems = state.categories,
                onItemClicked = onNavigationRequested
            )
            if (state.isLoading)
                LoadingCircleBox()
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinMVVMTheme {
        FoodCategoriesScreen(FoodCategoriesContract.State(), null) { }
    }
}