package com.lirctek.loadboard.ui.offers.offersCommonUi

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.shimmer.shimmerEffect
import com.lirctek.loadboard.ui.shimmer.shimmerEffectTwo
import com.lirctek.loadboard.ui.theme.CardBackgroundColor
import com.lirctek.loadboard.ui.theme.Gray

@Composable
fun OfferItemsShimmerUi(){
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
        ) {

            Column(
                modifier = Modifier.shimmerEffectTwo().padding(10.dp)
            ) {
                Row {
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
                    LocationShimmerUi()
                }
                Spacer(modifier = Modifier.height(10.dp))
                CustomerMilesShimmerUi()
                Spacer(modifier = Modifier.height(5.dp))
                BookAndOfferShimmerUi()
                Spacer(modifier = Modifier.height(5.dp))
                //YourAndOfferShimmerUi()

            }
        }

    }
}

@Composable
fun YourAndOfferShimmerUi(){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column{
            OfferButtonShimmerUi(text = "Your Offer", value = "350.0", Icons.Outlined.EditNote)
        }
        Column(
            modifier = Modifier.fillMaxWidth().shimmerEffect(),
            horizontalAlignment = Alignment.End
        ){
            OfferButtonShimmerUi(text = "Accept Offer", icon = Icons.Outlined.AdsClick)
        }
    }
}

@Composable
fun OfferButtonShimmerUi(
    text: String,
    value: String? = null,
    icon: ImageVector? = null
){

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
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
        if (value != null) {
            Text(
                text = " : $ $value",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }

}

@Composable
fun BookAndOfferShimmerUi() {
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
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Text(
                    text = "",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .shimmerEffect()
                        .width(140.dp)
                )
            }
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
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primary,
            ){
                Text(
                    text = "",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End,
                    fontSize = 16.sp,
                    modifier = Modifier.shimmerEffect().width(140.dp)
                )
            }

        }
    }
}

@Composable
fun CustomerMilesShimmerUi() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column{
            Text(
                text = "Customer".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Text(
                    text = "",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .shimmerEffect()
                        .width(140.dp)
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
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Text(
                    text = "",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .shimmerEffect()
                        .width(140.dp)

                )
            }
        }
    }
}

@Composable
fun LocationShimmerUi() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ){
        Column() {
            StopShimmerUi(type = "Pick Up")
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            StopShimmerUi(type = "Delivery")
        }
    }
}

@Composable
fun StopShimmerUi(
    type: String
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
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Text(
                    text = "",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .shimmerEffect()
                        .width(100.dp)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ){
            Card(
                shape = RoundedCornerShape(5.dp),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Text(
                    text = "",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .shimmerEffect()
                        .width(100.dp)
                )
            }
        }
    }
}

