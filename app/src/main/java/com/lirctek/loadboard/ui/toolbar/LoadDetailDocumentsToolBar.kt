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
fun LoadDetailDocumentsToolBar(loadData: LoadsList){

    TopAppBar(
        title = { Text(
            text = "Documents".uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.offset(x = (-16).dp)
        )
        },
        navigationIcon = {
            IconButton(onClick = {
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                )
            }
        },
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
        elevation = 0.dp
    )
}