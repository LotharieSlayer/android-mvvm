package net.lotharie.kotlin_mvvm.ui.components.organisms.food

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil3.request.ImageRequest
import net.lotharie.kotlin_mvvm.model.FoodItem
import net.lotharie.kotlin_mvvm.ui.components.molecules.food.FoodItemRow

@Composable
fun FoodItemList(
    foodItems: List<FoodItem>,
    onItemClicked: (id: String) -> Unit = { },
    expandableItems: Boolean = true,
    iconTransformationBuilder: (ImageRequest.Builder.() -> Unit) = { } // Default to empty transformation builder
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(foodItems) { item ->
            FoodItemRow(
                item = item,
                itemShouldExpand = expandableItems,
                onItemClicked = onItemClicked,
                iconTransformationBuilder = iconTransformationBuilder
            )
        }
    }
}