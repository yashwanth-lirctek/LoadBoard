package com.lirctek.loadboard.ui.offers.offerDetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.noData.NoDataScreen
import com.lirctek.loadboard.ui.toolbar.HomeOtherToolBar
import com.lirctek.loadboard.ui.toolbar.OffersToolBar

@Composable
fun OfferDetails(navController: NavHostController, offerItem: OfferDataList) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            OffersToolBar(offerItem)
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colors.background)
                .padding(10.dp)
        ) {
            Row() {
                if (offerItem.offeredAmount == 0.0){
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Previous Offer".uppercase(),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = " - ",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OfferButtonUi("Place Offer")
                    }
                }else {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Previous Offer".uppercase(),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "$ ${offerItem.offeredAmount}",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OfferButtonUi("Edit Offer")
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Book Now".uppercase(),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "$ ${offerItem.bookNowAmount}",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OfferButtonUi("Accept Offer")
                }
            }
        }
    }

}

@Composable
fun OfferButtonUi(
    text: String,
    modifier: Modifier = Modifier
){

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(
            text = text.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = MaterialTheme.colors.primary
        )
    }

}