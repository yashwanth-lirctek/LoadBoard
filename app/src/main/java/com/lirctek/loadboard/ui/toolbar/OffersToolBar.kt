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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun OffersToolBar(item: OfferDataList){
    Box {
        TopAppBar(
            title = {
                Text(
                    text = if (item.loadNumber != null) "#${item.loadNumber}" else "-",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.offset(x = (-16).dp)
                )
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
                ToolBarRight(item)
            },
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 0.dp
        )
    }
}

@Composable
fun ToolBarRight(item: OfferDataList) {
    Row{
        Column(
            modifier = Modifier.width(70.dp)
        ){
            Text(
                text = "Offers".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = item.totalOffers.toString(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.width(90.dp)
        ){
            Text(
                text = "Lowest Offer".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "$ ${item.lowestOfferAmount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
    }
}