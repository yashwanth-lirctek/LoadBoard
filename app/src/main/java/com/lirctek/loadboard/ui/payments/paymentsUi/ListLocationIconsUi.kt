package com.lirctek.loadboard.ui.payments.paymentsUi

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.WhereToVote
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ListLocationIconsUi(){
    Column() {
        Icon(
            imageVector = Icons.Filled.Pin,
            contentDescription = null,
            tint = Color.White,
        )
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            imageVector = Icons.Filled.WhereToVote,
            contentDescription = null,
            tint = Color.White
        )
    }
}