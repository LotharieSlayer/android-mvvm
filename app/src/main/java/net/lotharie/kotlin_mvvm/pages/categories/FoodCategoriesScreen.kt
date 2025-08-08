package net.lotharie.kotlin_mvvm.pages.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import net.lotharie.kotlin_mvvm.model.CategoryItem
import net.lotharie.kotlin_mvvm.ui.components.atoms.LoadingCircleBox
import net.lotharie.kotlin_mvvm.ui.components.organisms.appbar.CategoriesAppBar
import net.lotharie.kotlin_mvvm.ui.components.organisms.food.FoodItemList
import net.lotharie.kotlin_mvvm.ui.components.organisms.food.Item
import net.lotharie.kotlin_mvvm.ui.navigation.NavigationKeys
import net.lotharie.kotlin_mvvm.ui.theme.KotlinMVVMTheme


@Composable
internal fun FoodCategoriesScreen(
    onDetailsClick: (String) -> Unit,
    viewModel: FoodCategoriesViewModel = hiltViewModel()
) {
    val state by viewModel.categoriesUiState.collectAsStateWithLifecycle()
    FoodCategoriesScreen(
        state = state,
        onDetailsClick = onDetailsClick
    )
}
@Composable
fun FoodCategoriesScreen(
    state: CategoriesUiState,
    onDetailsClick: (itemId: String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { CategoriesAppBar() }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is CategoriesUiState.Loading -> LoadingCircleBox()
                is CategoriesUiState.Success -> FoodItemList(
                    foodItems = state.categories.map { item ->
                        Item.Category(
                            categoryItem = CategoryItem(
                                id = item.id,
                                name = item.name,
                                description = item.description,
                                thumbnailUrl = item.thumbnailUrl
                            )
                        )
                    },
                    onItemClicked = onDetailsClick
                )

                is CategoriesUiState.Error -> {
                    LaunchedEffect(state.exception) {
                        snackbarHostState.showSnackbar(
                            message = "Error: ${state.exception.message}",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodCategoriesScreenPreview() {
    KotlinMVVMTheme {
        FoodCategoriesScreen(
            state = CategoriesUiState.Success(
                categories = listOf(
                    CategoryItem(
                        id = "1",
                        name = "Fruits",
                        description = "Fresh and healthy fruits",
                        thumbnailUrl = ""
                    ),
                    CategoryItem(
                        id = "2",
                        name = "Vegetables",
                        description = "Organic and fresh vegetables",
                        thumbnailUrl = ""
                    )
                )
            ),
            onDetailsClick = {}
        )
    }
}