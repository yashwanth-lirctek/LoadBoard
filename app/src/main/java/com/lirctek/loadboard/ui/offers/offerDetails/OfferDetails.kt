package com.lirctek.loadboard.ui.offers.offerDetails

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.WhereToVote
import androidx.compose.runtime.*
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
import com.lirctek.loadboard.ui.dialog.FinalScoreDialog
import com.lirctek.loadboard.ui.noData.NoDataScreen
import com.lirctek.loadboard.ui.offers.dialogs.AcceptOfferDialog
import com.lirctek.loadboard.ui.offers.offersCommonUi.LocationUi
import com.lirctek.loadboard.ui.payments.paymentsUi.ListLocationIconsUi
import com.lirctek.loadboard.ui.toolbar.HomeOtherToolBar
import com.lirctek.loadboard.ui.toolbar.OffersToolBar
import kotlinx.coroutines.launch

@Composable
fun OfferDetails(navController: NavHostController, offerItem: OfferDataList) {

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    var openDialog  by remember { mutableStateOf(false) }
    AcceptOfferDialog(
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
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colors.background)
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
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
            StopDetailsUi(offerItem)
            Spacer(modifier = Modifier.height(10.dp))
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
fun AcceptOfferDialog(
    offerDataList: OfferDataList,
    onAcceptOfferOk: (offerItem: OfferDataList) -> Unit
){
    var openDialog  by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
            },
            title = { Text("Offer") },
            text = { Text("Are you sure?\nDo you want to accept offer at $ ${offerDataList.bookNowAmount}") },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                        onAcceptOfferOk(offerDataList)
                    }
                ) {
                    Text(text = "Yes")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                }) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}

@Composable
fun StopDetailsUi(offerItem: OfferDataList){
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
                    fontSize = 14.sp
                )
                Text(
                    text = if (offerItem.customerName != null) offerItem.customerName!! else "-",
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
                    text = "Phone".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(
                    text = if (offerItem.customerPhone != null) offerItem.customerPhone!! else "-",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
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
                    fontSize = 14.sp
                )
                Text(
                    text = if (offerItem.equipment != null) offerItem.equipment!! else "-",
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
                    text = "Temperature".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(
                    text = "----Not Available----",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
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
                    fontSize = 14.sp
                )
                Text(
                    text = offerItem.totalMiles.toString(),
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
                    text = "Total Weight".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(
                    text = "----Not Available----",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
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
                    fontSize = 14.sp
                )
                Text(
                    text = "----Need To Check----",
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
                    text = "No. Deliveries".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(
                    text = "----Need To Check----",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
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
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(
            text = text.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary
        )
    }

}