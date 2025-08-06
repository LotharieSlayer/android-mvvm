package net.lotharie.kotlin_mvvm.ui.components.molecules.food

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi
import coil3.request.ImageRequest
import net.lotharie.kotlin_mvvm.model.FoodItem
import net.lotharie.kotlin_mvvm.noRippleClickable
import net.lotharie.kotlin_mvvm.ui.components.atoms.ExpandableIcon

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FoodItemRow(
    item: FoodItem,
    itemShouldExpand: Boolean = false,
    iconTransformationBuilder: ImageRequest.Builder.() -> Unit = { },
    onItemClicked: (id: String) -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onItemClicked(item.id) }
    ) {
        var expanded by rememberSaveable { mutableStateOf(false) }
        Row(modifier = Modifier.animateContentSize()) {
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                FoodItemThumbnail(item.thumbnailUrl, iconTransformationBuilder)
            }
            FoodItemDetails(
                item = item,
                expandedLines = if (expanded) 10 else 2,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    )
                    .fillMaxWidth(0.80f)
                    .align(Alignment.CenterVertically)
            )
            if (itemShouldExpand)
                Box(
                    modifier = Modifier
                        .align(if (expanded) Alignment.Bottom else Alignment.CenterVertically)
                        .noRippleClickable { expanded = !expanded }
                ) {
                    ExpandableIcon(expanded)
                }
        }
    }
}
