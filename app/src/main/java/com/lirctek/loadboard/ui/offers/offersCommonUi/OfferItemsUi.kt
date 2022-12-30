package com.lirctek.loadboard.ui.offers.offersCommonUi

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AdsClick
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.theme.CardBackgroundColor

@Composable
fun OfferItemsUi(
    item: OfferDataList,
    isActive: Boolean,
    onLayoutClick :(item: OfferDataList) -> Unit,
    onAcceptOffer :(item: OfferDataList) -> Unit,
    onPlaceOffer :(item: OfferDataList) -> Unit,
    onYourOffer :(item: OfferDataList) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 2.dp,
            backgroundColor = CardBackgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onLayoutClick(item)
                }
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 0.dp,
                        backgroundColor = MaterialTheme.colors.primary,
                    ) {
                        Box(
                            modifier = Modifier.padding(horizontal = 2.dp, vertical = 5.dp)) {
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
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    LocationUi(item)
                }
                Spacer(modifier = Modifier.height(10.dp))
                CustomerMilesUi(item)
                Spacer(modifier = Modifier.height(5.dp))
                BookAndOfferUi(item)
                if (isActive) {
                    Spacer(modifier = Modifier.height(5.dp))
                    YourAndOfferUi(
                        item = item,
                        onAcceptOffer = {
                            onAcceptOffer(it)
                        },
                        onPlaceOffer = {
                            onPlaceOffer(it)
                        },
                        onYourOffer = {
                            onYourOffer(it)
                        }
                    )
                }

            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
                backgroundColor = Color.White,
                modifier = Modifier
                    .width(100.dp)
            ){
                Text(
                    text = if (item.loadNumber != null) item.loadNumber!! else "-",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
        }

    }
}

@Composable
fun YourAndOfferUi(
    item: OfferDataList,
    onAcceptOffer :(item: OfferDataList) -> Unit,
    onPlaceOffer :(item: OfferDataList) -> Unit,
    onYourOffer :(item: OfferDataList) -> Unit
){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (item.offeredAmount == 0.0){
            Column (
            ){
                OfferButtonUi(text = "Place Offer", icon = Icons.Outlined.AdsClick,
                    modifier = Modifier
                        .clickable {
                            onPlaceOffer(item)
                        })
            }
        }else {
            Column(
            ) {
                OfferButtonUi(text = "Your Offer", value = item.offeredAmount.toString(), Icons.Outlined.EditNote,
                    modifier = Modifier
                        .clickable {
                            onYourOffer(item)
                        })
            }
        }

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
        ){
            OfferButtonUi(text = "Accept Offer", icon = Icons.Outlined.AdsClick,
                modifier = Modifier
                    .clickable {
                        onAcceptOffer(item)
                    })
        }
    }
}

@Composable
fun OfferButtonUi(
    text: String,
    value: String? = null,
    icon: ImageVector? = null,
    modifier: Modifier
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
        if(icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp)
            )
        }
        Text(
            text = text.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = if (value != null) " : $ $value" else "-",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }

}

@Composable
fun BookAndOfferUi(item: OfferDataList) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column{
            Text(
                text = "Lowest Offer".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "$ ${item.lowestOfferAmount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = "Book Now".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "$ ${item.bookNowAmount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CustomerMilesUi(item: OfferDataList) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Customer".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            if (item.customerName != null){
                Text(
                    text = item.customerName!!,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = "Loaded Miles".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = item.totalMiles.toString(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun LocationUi(item: OfferDataList) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ){
        Column() {
            StopUi(type = "Pick Up", location = item.pickup, time = item.pickupFromDate)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            StopUi(type = "Delivery", location = item.delivery, time = item.deliveryFromDate)
        }
    }
}

@Composable
fun StopUi(
    type: String,
    location: String?,
    time: String?
){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column{
            Text(
                text = type.uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = location ?: "-",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ){
            if (time != null) {
                Text(
                    text = time,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        }
    }
}