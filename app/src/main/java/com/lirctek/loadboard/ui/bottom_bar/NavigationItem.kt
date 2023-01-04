package com.lirctek.loadboard.ui.bottom_bar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.theme.Gray
import com.lirctek.loadboard.ui.theme.ProgressBarWhiteColor

@Composable
fun BottomNav(navController: NavController) {

    var currentSelectedScreenId by remember {
        mutableStateOf(5)
    }

    BottomAppBar(
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
        cutoutShape = CircleShape,
        contentPadding = PaddingValues(horizontal = 10.dp),
        elevation = 2.dp,
        modifier = Modifier.height(70.dp)
    ) {

        BottomItems.OFFER.let { offers ->
            NavItem(
                item = offers,
                isSelected = offers.id == currentSelectedScreenId) {
                if (currentSelectedScreenId != offers.id) {
                    currentSelectedScreenId = offers.id
                    navController.navigate("main/offers")
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.2f))
        BottomItems.LOADS.let { loads ->
            NavItem(item = loads, isSelected = loads.id == currentSelectedScreenId) {
                if (currentSelectedScreenId != loads.id) {
                    currentSelectedScreenId = loads.id
                    navController.navigate("main/loads")
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomItems.PAYMENT.let { payment ->
            NavItem(item = payment, isSelected = payment.id == currentSelectedScreenId) {
                if (currentSelectedScreenId != payment.id) {
                    currentSelectedScreenId = payment.id
                    navController.navigate("main/payments")
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.2f))
        BottomItems.SETTINGS.let { settings ->
            NavItem(item = settings, isSelected = settings.id == currentSelectedScreenId) {
                if (currentSelectedScreenId != settings.id) {
                    currentSelectedScreenId = settings.id
                    navController.navigate("main/settings")
                }
            }
        }

    }

}

@Composable
fun NavItem(
    item: BottomItems,
    isSelected: Boolean,
    onClick:() -> Unit,
){
    val icon = if (isSelected) item.selectedIcon else item.unSelectedIcon
    val iconAlpha = if (isSelected) 1f else 0.5f
    IconButton(
        onClick = { onClick() },
        modifier = Modifier.padding(5.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector  = icon, item.title, tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface.copy(alpha = iconAlpha))
            Text(
                text = item.title.uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    }
}

@Composable
fun FabButton(navController: NavHostController) {

    FloatingActionButton(
        onClick = {},
        backgroundColor = Gray,
        elevation = FloatingActionButtonDefaults.elevation(2.dp, 4.dp, 2.dp, 2.dp),

        ) {
        IconButton(
            onClick = {
                navController.navigate("main/home")
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home",
                tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun FabButtonDocuments(imageVector: ImageVector, description: String, onClick: () -> Unit) {

    FloatingActionButton(
        onClick = {onClick()},
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
        elevation = FloatingActionButtonDefaults.elevation(2.dp, 4.dp, 2.dp, 2.dp),
        ) {
        Icon(
            imageVector = imageVector,
            contentDescription = description,
            tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun FabButtonEditOffer(imageVector: ImageVector, description: String, onClick: () -> Unit) {

    ExtendedFloatingActionButton(
        text = {
            Text(
                text = "Add Description".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
            )
        },
        onClick = {onClick()},
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
        elevation = FloatingActionButtonDefaults.elevation(2.dp, 4.dp, 2.dp, 2.dp),
        icon = {
            Icon(
                imageVector = imageVector,
                contentDescription = description,
                tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
            )
        }
    )
}