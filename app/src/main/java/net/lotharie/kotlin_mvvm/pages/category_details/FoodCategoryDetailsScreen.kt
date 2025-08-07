package net.lotharie.kotlin_mvvm.pages.category_details


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import net.lotharie.kotlin_mvvm.model.CategoryItem
import net.lotharie.kotlin_mvvm.model.MealItem
import net.lotharie.kotlin_mvvm.ui.components.atoms.LoadingCircleBox
import net.lotharie.kotlin_mvvm.ui.components.organisms.appbar.CategoryDetailsCollapsingAppBar
import net.lotharie.kotlin_mvvm.ui.components.organisms.food.FoodItemList
import net.lotharie.kotlin_mvvm.ui.components.organisms.food.Item
import kotlin.math.min


@Composable
fun FoodCategoryDetailsScreen(state: CategoryDetailsUiState) {
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            when (state) {
                is CategoryDetailsUiState.Success -> {
                    Surface(shadowElevation = 4.dp) {
                        CategoryDetailsCollapsingAppBar(
                            state.categoryDetailsData.category,
                            scrollOffset
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    FoodItemList(
                        foodItems = state.categoryDetailsData.categoryMealItems.map { item ->
                            Item.Food(
                                mealItem = MealItem(
                                    id = item.id,
                                    name = item.name,
                                    description = item.description,
                                    thumbnailUrl = item.thumbnailUrl
                                )
                            )
                        },
                        expandableItems = false,
                        iconTransformationBuilder = {
                            transformations(
                                CircleCropTransformation()
                            )
                        }
                    )
                }

                is CategoryDetailsUiState.Loading -> {
                    LoadingCircleBox()
                }

                is CategoryDetailsUiState.Error -> {
                    // Show an error message
                }
            }
        }
    }
}

@Preview
@Composable
fun FoodCategoryDetailsScreenPreview() {
    FoodCategoryDetailsScreen(
        state = CategoryDetailsUiState.Success(
            categoryDetailsData = CategoryDetailsData(
                category = CategoryItem(
                    id = "1",
                    name = "Fruits",
                    description = "Fresh and healthy fruits",
                    thumbnailUrl = ""
                ),
                categoryMealItems = listOf(
                    MealItem(
                        id = "1",
                        name = "Banana",
                        description = "Generous banana",
                        thumbnailUrl = ""
                    ),
                    MealItem(
                        id = "2",
                        name = "Pineapple",
                        description = "Juicy pineapple",
                        thumbnailUrl = ""
                    )
                )
            )
        )
    )
}