package com.klt.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klt.ui.navigation.ActiveTask
import com.klt.ui.navigation.Tasks
import com.klt.util.ICustomer
import com.klt.util.IKLTItem
import com.klt.util.ITask
import kotlinx.coroutines.launch

@Composable
fun EntryCard(
    item: Any,
    textColor: Color,
    navController: NavController,
    destination: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
    hasIcon: Boolean = true,
    isInsideDrawer: Boolean = false,
    icon: ImageVector? = null,
    job: (item: IKLTItem?) -> Unit = { }
) {
    val coroutine = rememberCoroutineScope()
    var cardColor = remember { mutableStateOf(backgroundColor) }

    val text = when (item) {
        is ICustomer -> item.name
        is ITask -> item.name
        is String -> item
        else -> ""
    }

    Button(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .height(50.dp),
        onClick = {
            if (isInsideDrawer) coroutine.launch { job(null) }
            if (item is ICustomer) Tasks.customer = item
            if (item is ITask) ActiveTask.task = item
            navController.navigate(destination)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = cardColor.value
        ),
        shape = RoundedCornerShape(5.dp),
        elevation = null
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier)
                .background(Color.Transparent)
        ) {
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = text, color = textColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (hasIcon) {
                IconButton(onClick = {
                    coroutine.launch { job(item as IKLTItem) }
                    // if (isInsideDrawer) coroutine.launch { job() }
                    // navController.navigate(destination)
                }) {
                    Icon(
                        imageVector = when (item) {
                            is ICustomer -> if (item.pinned) Icons.Outlined.Star else Icons.Outlined.StarBorder
                            is ITask ->  if (item.pinned) Icons.Outlined.Star else Icons.Outlined.StarBorder
                            else -> icon ?: Icons.Default.BrokenImage // in case of error
                        }, contentDescription = "card-icon", tint = textColor
                    )
                }
            }
        }
    }
}
