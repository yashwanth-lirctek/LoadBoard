package com.lirctek.loadboard.ui.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DocumentScanner
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomItems (
        val id: Int,
        val title: String,
        val selectedIcon: ImageVector,
        val unSelectedIcon: ImageVector
    ){

    object OFFER : BottomItems(
        id = 0,
        title = "Offer",
        selectedIcon = Icons.Outlined.LocalShipping,
        unSelectedIcon = Icons.Filled.LocalShipping
    )

    object LOADS : BottomItems(
        id = 1,
        title = "My Loads",
        selectedIcon = Icons.Outlined.LocalShipping,
        unSelectedIcon = Icons.Filled.LocalShipping
    )

    object PAYMENT : BottomItems(
        id = 2,
        title = "Payment",
        selectedIcon = Icons.Outlined.Payments,
        unSelectedIcon = Icons.Filled.Payments
    )

    object SETTINGS : BottomItems(
        id = 3,
        title = "Settings",
        selectedIcon = Icons.Outlined.Settings,
        unSelectedIcon = Icons.Filled.Settings
    )
}

sealed class LoadDetailsBottomItems(
    val id: Int,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
){
    object LoadDetails : BottomItems(
        id = 0,
        title = "Load Details",
        selectedIcon = Icons.Outlined.LocalShipping,
        unSelectedIcon = Icons.Filled.LocalShipping
    )

    object Documents : BottomItems(
        id = 2,
        title = "Documents",
        selectedIcon = Icons.Outlined.DocumentScanner,
        unSelectedIcon = Icons.Filled.DocumentScanner
    )
}