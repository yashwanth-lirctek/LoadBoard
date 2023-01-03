package com.lirctek.loadboard.ui.offers.offerDetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.offers.dialogs.AcceptOfferDetailDialog
import com.lirctek.loadboard.ui.offers.offersCommonUi.LocationUi
import com.lirctek.loadboard.ui.payments.paymentsUi.ListLocationIconsUi
import com.lirctek.loadboard.ui.toolbar.OffersToolBar

@Composable
fun OfferDetails(navController: NavHostController, offerItem: OfferDataList) {

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    var openDialog  by remember { mutableStateOf(false) }
    AcceptOfferDetailDialog(
        openDialog = openDialog,
        offerDataList = offerItem,
        onAcceptOffer = {
            openDialog = false
        },
        onDismissOffer = {
            openDialog = false
        }
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            OffersToolBar(offerItem)
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
                text = "Load Details".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
            StopDetailsUi(offerItem)
            Spacer(modifier = Modifier.height(20.dp))
            LoadDetailsUI(offerItem)
            Spacer(modifier = Modifier.height(15.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(15.dp))
            ButtonsLayout(
                offerItem = offerItem,
                onPlaceOffer = {

                },
                onEditOffer = {

                },
                onAcceptOffer = {
                    openDialog = true
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun StopDetailsUi(offerItem: OfferDataList){
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
        LocationUi(offerItem)
    }
}

@Composable
fun LoadDetailsUI(offerItem: OfferDataList){
    Column {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column{
                Text(
                    text = "Broker Name".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = if (offerItem.customerName != null) offerItem.customerName!! else "-",
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
                    text = "Phone".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = if (offerItem.customerPhone != null) offerItem.customerPhone!! else "-",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column{
                Text(
                    text = "Equipment".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = if (offerItem.equipment != null) offerItem.equipment!! else "-",
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
                    text = "Temperature".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = "----Not Available----",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column{
                Text(
                    text = "Loaded Miles".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = offerItem.totalMiles.toString(),
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
                    text = "Total Weight".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = "----Not Available----",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column{
                Text(
                    text = "No. Pick Ups".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = "----Need To Check----",
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
                    text = "No. Deliveries".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = "----Need To Check----",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
                )
            }
        }
    }
}

@Composable
fun ButtonsLayout(
    offerItem: OfferDataList,
    onPlaceOffer: (offerItem: OfferDataList) -> Unit,
    onEditOffer: (offerItem: OfferDataList) -> Unit,
    onAcceptOffer: (offerItem: OfferDataList) -> Unit
){
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
                    textAlign = TextAlign.Center,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = " - ",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
                )
                Spacer(modifier = Modifier.height(6.dp))
                OfferButtonUi("Place Offer",
                    modifier = Modifier
                        .clickable {
                            onPlaceOffer(offerItem)
                        })
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
                    textAlign = TextAlign.Center,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "$ ${offerItem.offeredAmount}",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
                )
                Spacer(modifier = Modifier.height(6.dp))
                OfferButtonUi("Edit Offer",
                    modifier = Modifier
                        .clickable {
                            onEditOffer(offerItem)
                        })
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
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "$ ${offerItem.bookNowAmount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
            )
            Spacer(modifier = Modifier.height(6.dp))
            OfferButtonUi("Accept Offer",
                modifier = Modifier
                .clickable {
                    onAcceptOffer(offerItem)
                })
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
                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(
            text = text.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
        )
    }

}