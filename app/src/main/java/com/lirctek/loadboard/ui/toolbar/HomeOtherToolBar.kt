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
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
        )
        },
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
        elevation = 0.dp
    )
}