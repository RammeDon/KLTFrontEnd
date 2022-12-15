package com.klt.ui.composables


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klt.screens.KLTItem

@Composable
fun LazyWindow(
    modifier: Modifier = Modifier,
    navController: NavController,
    destination: String,
    items: List<KLTItem>,
    repeats: Int = 1,
    color: Color,
    icon: ImageVector? = null,
    collapsed: Boolean = false
) {

    val collapsedModif = Modifier.height(0.dp)
    val openedModif = Modifier.heightIn(0.dp, LocalConfiguration.current.screenHeightDp.dp - 270.dp)
    val defaultTween = 1000

    LazyColumn(
        modifier = if (collapsed) collapsedModif else openedModif //defaultTween is a constant set to 650
    ) {
        items(items = items, key = { client -> client.id }) { item ->
            repeat(repeats) {
                EntryCard(
                    text = item.name,
                    navController = navController,
                    destination = destination,
                    id = item.id,
                    icon = icon,
                    color = color
                )
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
    }
}
