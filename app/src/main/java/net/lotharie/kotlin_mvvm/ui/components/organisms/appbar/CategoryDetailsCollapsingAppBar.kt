package net.lotharie.kotlin_mvvm.ui.components.organisms.appbar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import net.lotharie.kotlin_mvvm.model.CategoryItem
import net.lotharie.kotlin_mvvm.ui.components.molecules.food.FoodItemDetails
import net.lotharie.kotlin_mvvm.ui.components.organisms.food.Item

@Composable
fun CategoryDetailsCollapsingAppBar(
    category: CategoryItem?,
    scrollOffset: Float,
) {
    val imageSize by animateDpAsState(targetValue = max(72.dp, 128.dp * scrollOffset))
    Row {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = CircleShape,
            border = BorderStroke(
                width = 2.dp,
                color = Color.Black
            ),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category?.thumbnailUrl)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = "Food category thumbnail picture",
                modifier = Modifier.size(max(72.dp, imageSize))
            )
        }
        FoodItemDetails(
            item = category?.let {
                Item.Category(
                    categoryItem = CategoryItem(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        thumbnailUrl = it.thumbnailUrl
                    )
                )
            },
            expandedLines = (kotlin.math.max(3f, scrollOffset * 6)).toInt(),
            modifier = Modifier
                .padding(
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
        )
    }
}
