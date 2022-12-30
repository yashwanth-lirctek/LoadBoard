package com.lirctek.loadboard.ui.toolbar

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun HomeOtherToolBar(text: String){

    TopAppBar(
        title = { Text(
            text = text.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.White
        )
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp
    )
}