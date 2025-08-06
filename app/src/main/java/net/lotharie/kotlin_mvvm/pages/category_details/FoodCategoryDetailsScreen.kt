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
import net.lotharie.kotlin_mvvm.ui.components.organisms.appbar.CategoryDetailsCollapsingAppBar
import net.lotharie.kotlin_mvvm.ui.components.organisms.food.FoodItemList
import kotlin.math.min


@Composable
fun FoodCategoryDetailsScreen(state: FoodCategoryDetailsContract.State) {
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            Surface(shadowElevation = 4.dp) {
                CategoryDetailsCollapsingAppBar(state.category, scrollOffset)
            }
            Spacer(modifier = Modifier.height(2.dp))
            FoodItemList(foodItems = state.categoryFoodItems,
                expandableItems = false,
                iconTransformationBuilder = {
                    transformations(
                        CircleCropTransformation()
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun FoodCategoryDetailsScreenPreview() {
    FoodCategoryDetailsScreen(
        state = FoodCategoryDetailsContract.State(
            category = null,
            categoryFoodItems = emptyList()
        )
    )
}