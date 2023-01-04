package com.lirctek.loadboard.ui.commonUi.floatingButtons

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun ExtendedFloatingActionButtonUI(
    text: String,
    imageVector: ImageVector,
    imageDescription: String,
    onClickExtendedFloatingActionButton : () -> Unit
){
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = text.uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        onClick = {
            onClickExtendedFloatingActionButton()
        },
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = FloatingActionButtonDefaults.elevation(2.dp, 4.dp, 2.dp, 2.dp),
        icon = {
            Icon(
                imageVector = imageVector,
                contentDescription = imageDescription,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}