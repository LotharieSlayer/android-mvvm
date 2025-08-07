package net.lotharie.kotlin_mvvm.ui.components.organisms.food

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil3.request.ImageRequest
import net.lotharie.kotlin_mvvm.model.CategoryItem
import net.lotharie.kotlin_mvvm.model.MealItem
import net.lotharie.kotlin_mvvm.ui.components.molecules.food.FoodItemRow

@Composable
fun FoodItemList(
    foodItems: List<Item>,
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

sealed class Item(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = ""
) {
    data class Food(
        val mealItem: MealItem
    ) : Item(
        id = mealItem.id,
        name = mealItem.name,
        thumbnailUrl = mealItem.thumbnailUrl,
        description = mealItem.description
    )

    data class Category(
        val categoryItem: CategoryItem
    ) : Item(
        id = categoryItem.id,
        name = categoryItem.name,
        thumbnailUrl = categoryItem.thumbnailUrl,
        description = categoryItem.description
    )
}