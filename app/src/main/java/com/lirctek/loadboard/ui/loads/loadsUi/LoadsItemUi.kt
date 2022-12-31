package com.lirctek.loadboard.ui.loads.loadsUi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.WhereToVote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.offers.offersCommonUi.StopUi
import com.lirctek.loadboard.ui.payments.paymentsUi.ListLocationIconsUi
import com.lirctek.loadboard.ui.theme.CardBackgroundColor

@Composable
fun LoadsItemUi(type: String) {
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
                            ListLocationIconsUi()
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    LocationUi()
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (type.equals("Available", ignoreCase = true)) {
                    CustomerBottomUi()
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
                    text = "#7435674",
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
fun CustomerBottomUi(){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Equipment".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "Full Truck Load",
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
                text = "Loaded Miles".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
            Text(
                text = "000",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun LocationUi() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ){
        Column() {
            StopUi(type = "Pick Up", location = "Bangalore, KA", time = "Aug, 18 Thue 2002")
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            StopUi(type = "Delivery", location = "Bangalore, KA", time = "Aug, 18 Thue 2002")
        }
    }
}