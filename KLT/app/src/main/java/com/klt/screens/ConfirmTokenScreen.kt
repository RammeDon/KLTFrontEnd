package com.klt.screens

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klt.ui.composables.NormalTextField
import com.klt.ui.navigation.ResetPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ConfirmTokenScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    OnSelfClick: () -> Unit = {}
) {
    var inputToken by remember { mutableStateOf("") }
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        NormalTextField(
            labelText = "example@klt.se", title = "Email", modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            updateState = { inputToken = it }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onClick = {

            }
        ) {
            Text("Reset Password")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}