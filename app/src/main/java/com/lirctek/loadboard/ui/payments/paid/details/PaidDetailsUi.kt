package com.lirctek.loadboard.ui.payments.paid.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.offers.offerDetails.StopDetailsUi
import com.lirctek.loadboard.ui.offers.offersCommonUi.StopUi
import com.lirctek.loadboard.ui.payments.paymentsUi.ListLocationIconsUi
import com.lirctek.loadboard.ui.payments.paymentsUi.PaidBottomUi
import com.lirctek.loadboard.ui.payments.paymentsUi.PaidMiddleUi
import com.lirctek.loadboard.ui.theme.CardBackgroundColor
import com.lirctek.loadboard.ui.toolbar.OffersToolBar
import com.lirctek.loadboard.ui.toolbar.PaymentsPaidToolBar

@Composable
fun PaidDetailsUi(){

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentsPaidToolBar()
        },
        scaffoldState = scaffoldState,
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
                .verticalScroll(scrollState)
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Load - 4989".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
            StopDetailsUi()
            Spacer(modifier = Modifier.height(15.dp))
            PaidDetailsMainUi()
            Spacer(modifier = Modifier.height(15.dp))
            PaidMiddleUi("Invoiced", "200", "ADD")
            Spacer(modifier = Modifier.height(3.dp))
            PaidMiddleUi("Reimbursement", "250", "ADD")
            Spacer(modifier = Modifier.height(3.dp))
            PaidMiddleUi("Fees", "50", "SUB")
            Spacer(modifier = Modifier.height(3.dp))
            PaidMiddleUi("Advances", "100", "SUB")
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(5.dp))
            PaidBottomUi("300")

        }
    }

}

@Composable
fun PaidDetailsMainUi() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(60.dp)
        ){
            Text(
                text = "Tender Amount".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer

            )
            Text(
                text = "$ 2500",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
            )
        }
        Divider(color = Color.Gray, modifier = Modifier
            .height(60.dp)
            .width(1.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(60.dp)
        ){
            Text(
                text = "Invoiced".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = "$ 2500",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
            )
        }
        Divider(color = Color.Gray, modifier = Modifier
            .height(60.dp)
            .width(1.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(60.dp)
        ){
            Text(
                text = "Paid".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = "$ 3500",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}

@Composable
fun StopDetailsUi(){
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
}

@Composable
fun LocationUi() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ){
        Column() {
            StopUi(type = "Pick Up", location = "Bangalore KA", time = "Aug, 28 2022")
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            StopUi(type = "Delivery", location = "Bangalore KA", time = "Aug, 28 2022")
        }
    }
}