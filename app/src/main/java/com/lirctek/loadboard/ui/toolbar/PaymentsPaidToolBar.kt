package com.lirctek.loadboard.ui.toolbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun PaymentsPaidToolBar(){
    Box {
        TopAppBar(
            title = {
                ToolBarLeft()
            },
            navigationIcon = {
                IconButton(onClick = {
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            actions = {
                ToolBarRight()
            },
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 0.dp
        )
    }
}

@Composable
fun ToolBarRight() {
    Row{
        Column(
            modifier = Modifier.width(90.dp)
        ){
            Text(
                text = "Paid".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Text(
                text = "$ 5000",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
    }
}

@Composable
fun ToolBarLeft() {
    Row(
        modifier = Modifier.offset(x = (-16).dp)
    ){
        Column(){
            Text(
                text = "Settlement : 1645".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "Apr 21, 2000".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}