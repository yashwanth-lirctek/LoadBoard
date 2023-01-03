package com.lirctek.loadboard.ui.payments.paymentsUi

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.WhereToVote
import androidx.compose.material.icons.outlined.AdsClick
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.UploadFile
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
import com.lirctek.loadboard.ui.loads.loadsUi.CustomerBottomUi
import com.lirctek.loadboard.ui.offers.offersCommonUi.OfferButtonUi
import com.lirctek.loadboard.ui.offers.offersCommonUi.StopUi
import com.lirctek.loadboard.ui.payments.PaymentsConstants
import com.lirctek.loadboard.ui.theme.CardBackgroundColor

@Composable
fun PaymentsBilledAndInvoicedUi(
    type: String
){
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
                    LocationUi()
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (type.equals(PaymentsConstants.NOT_BILLED, ignoreCase = true)) {
                    NotBilledBottomUi()
                    Spacer(modifier = Modifier.height(5.dp))
                    NotBilledButtonUi()
                } else if (type.equals(PaymentsConstants.INVOICED, ignoreCase = true)){
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(10.dp))
                    InvoicedBottomUi()
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
                    text = "#7435674",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
        }

    }
}

@Composable
fun InvoicedBottomUi() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column{
            Text(
                text = "Invoiced On".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = "April 21 - 4 Days",
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
                text = "Estimated Pay Date".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = "April 28",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}

@Composable
fun NotBilledButtonUi() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        UploadDocumentButtonUi(text = "Upload Document", icon = Icons.Outlined.UploadFile,
            modifier = Modifier
                .clickable {
                })
    }
}

@Composable
fun UploadDocumentButtonUi(
    text: String,
    icon: ImageVector,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp)
        )
        Text(
            text = text.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
        )
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

@Composable
fun NotBilledBottomUi() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 5.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.EditNote,
                contentDescription = null,
                tint = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp)
            )
            Text(
                text = "Uploaded document is not good, Please upload document again.",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}
