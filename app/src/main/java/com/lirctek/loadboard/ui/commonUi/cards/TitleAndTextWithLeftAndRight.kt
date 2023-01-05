package com.lirctek.loadboard.ui.commonUi.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun TitleAndTextWithLeftAndRightUI(
    leftTitle: String,
    leftIcon: ImageVector? = null,
    leftValue: String? = null,
    rightTitle: String,
    rightIcon: ImageVector? = null,
    rightValue: String? = null
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TitleAndTextUI(title = leftTitle, icon = leftIcon, value = leftValue)
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ){
            TitleAndTextUI(title = rightTitle, icon = rightIcon, value = rightValue)
        }
    }
}

@Composable
fun TitleAndTextUI(
    title: String,
    icon: ImageVector? = null,
    value: String? = null,
){
    Column {
        Text(
            text = title.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Row() {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp)
                )
            }
            Text(
                text = (value ?: "-").uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}