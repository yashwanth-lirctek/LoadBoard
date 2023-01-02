package com.lirctek.loadboard.ui.home.homeDetails

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
import com.lirctek.loadboard.data.reqres.LoadBoardDataList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.home.dialogs.AcceptOfferHomeDialog
import com.lirctek.loadboard.ui.offers.offersCommonUi.StopUi
import com.lirctek.loadboard.ui.payments.paymentsUi.ListLocationIconsUi
import com.lirctek.loadboard.ui.toolbar.HomeDetailsToolBar

@Composable
fun HomeDetailsUi(navController: NavHostController, loadBoardDataList: LoadBoardDataList) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    var openDialog  by remember { mutableStateOf(false) }
    AcceptOfferHomeDialog(
        openDialog = openDialog,
        offerDataList = loadBoardDataList,
        onAcceptOffer = {
            openDialog = false
        },
        onDismissOffer = {
            openDialog = false
        }
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeDetailsToolBar(loadBoardDataList)
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
            StopDetailsUi(loadBoardDataList)
            Spacer(modifier = Modifier.height(10.dp))
            LoadDetailsUI(loadBoardDataList)
            Spacer(modifier = Modifier.height(15.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(15.dp))
            ButtonsLayout(
                offerItem = loadBoardDataList,
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
fun StopDetailsUi(offerItem: LoadBoardDataList){
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
fun LocationUi(item: LoadBoardDataList) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
    ){
        Column() {
            StopUi(type = "Pick Up", location = item.Pickup, time = item.PickupFromDate)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            StopUi(type = "Delivery", location = item.Delivery, time = item.DeliveryFromDate)
        }
    }
}

@Composable
fun LoadDetailsUI(offerItem: LoadBoardDataList){
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
                    text = if (offerItem.CustomerName != null) offerItem.CustomerName!! else "-",
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
                    text = if (offerItem.CustomerPhone != null) offerItem.CustomerPhone!! else "-",
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
                    text = if (offerItem.Equipment != null) offerItem.Equipment!! else "-",
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
                    text = offerItem.TotalMiles.toString(),
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
    offerItem: LoadBoardDataList,
    onPlaceOffer: (offerItem: LoadBoardDataList) -> Unit,
    onEditOffer: (offerItem: LoadBoardDataList) -> Unit,
    onAcceptOffer: (offerItem: LoadBoardDataList) -> Unit
){
    Row() {
        if (offerItem.OfferedAmount == 0.0){
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
                    text = "$ ${offerItem.OfferedAmount}",
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
                text = "$ ${offerItem.BookNowAmount}",
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