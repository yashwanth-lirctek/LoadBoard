package com.lirctek.loadboard.ui.toolbar

import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun LoadDetailToolBar(loadData: LoadsList){

    TopAppBar(
        title = { Text(
            text = "Load : ${loadData.dispatchNumber}".uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.offset(x = (-16).dp)
        )
        },
        navigationIcon = {
            IconButton(onClick = {
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "SaveAction",
                    tint = Color.White
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp
    )
}