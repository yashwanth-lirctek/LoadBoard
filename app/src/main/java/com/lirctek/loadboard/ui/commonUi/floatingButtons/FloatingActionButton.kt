package com.lirctek.loadboard.ui.commonUi.floatingButtons

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButtonUI(
    imageVector: ImageVector,
    imageDescription: String,
    onClickFloatingActionButton : () -> Unit
){

    FloatingActionButton(
        onClick = {
            onClickFloatingActionButton()
        },
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = FloatingActionButtonDefaults.elevation(2.dp, 4.dp, 2.dp, 2.dp),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = imageDescription,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}