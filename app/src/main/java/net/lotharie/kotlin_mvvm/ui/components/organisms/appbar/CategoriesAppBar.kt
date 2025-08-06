package net.lotharie.kotlin_mvvm.ui.components.organisms.appbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.lotharie.kotlin_mvvm.R

@Composable
fun CategoriesAppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Action icon",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = { Text(stringResource(R.string.app_name)) }
    )
}

