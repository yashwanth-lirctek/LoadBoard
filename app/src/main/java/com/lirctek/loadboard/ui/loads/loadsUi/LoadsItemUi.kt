package com.lirctek.loadboard.ui.loads.loadsUi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.offers.offersCommonUi.StopUi
import com.lirctek.loadboard.ui.payments.paymentsUi.ListLocationIconsUi
import com.lirctek.loadboard.ui.theme.CardBackgroundColor

@Composable
fun LoadsItemUi(
    type: String,
    item: LoadsList,
    onLayoutClick: (load: LoadsList) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 2.dp,
            backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(top = 15.dp)
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
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiary,
                    ) {
                        Box(
                            modifier = Modifier.padding(horizontal = 2.dp, vertical = 5.dp)) {
                            ListLocationIconsUi()
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    LocationUi(item)
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (type.equals("Available", ignoreCase = true)) {
                    CustomerBottomUi(item)
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
                backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .width(100.dp)
            ){
                Text(
                    text = if (item.dispatchNumber != null) item.dispatchNumber!! else "-",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
        }

    }
}

@Composable
fun CustomerBottomUi(item: LoadsList){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Load Type".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = if (item.loadType != null) item.loadType!! else "-",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
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
                fontSize = 12.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = "----Not Getting-------",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}

@Composable
fun LocationUi(item: LoadsList) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ){
        Column() {
            StopUi(type = "Pick Up", location = item.pickupLocation, time = item.pickupDate)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            StopUi(type = "Delivery", location = item.deliveryLocation, time = item.deliveryDate)
        }
    }
}