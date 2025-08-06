package net.lotharie.kotlin_mvvm.ui.components.molecules.food

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import net.lotharie.kotlin_mvvm.model.FoodItem

@Composable
fun FoodItemDetails(
    item: FoodItem?,
    expandedLines: Int,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = item?.name ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        if (item?.description?.trim()?.isNotEmpty() == true)
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                Text(
                    text = item.description.trim(),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = expandedLines
                )
            }
    }
}
