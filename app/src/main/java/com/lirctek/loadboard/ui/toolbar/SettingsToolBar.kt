package com.lirctek.loadboard.ui.toolbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun SettingsToolBar(navController: NavController, isEditButton: Boolean, isSaveButton: Boolean){

    TopAppBar(
        title = { Text(
            text = "SETTINGS",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.White
        )
        },
        actions = {
            if (isSaveButton){
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = "SaveAction",
                        tint = Color.White
                    )
                }
            }
            if (isEditButton) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.EditNote,
                        contentDescription = "EditAction",
                        tint = Color.White
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp
    )
}