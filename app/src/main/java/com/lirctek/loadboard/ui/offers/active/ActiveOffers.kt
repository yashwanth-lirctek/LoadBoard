package com.lirctek.loadboard.ui.offers.active

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson
import com.lirctek.loadboard.connectivity.ConnectivityObserver
import com.lirctek.loadboard.connectivity.NetworkConnectivityObserver
import com.lirctek.loadboard.ui.noData.NoDataScreen
import com.lirctek.loadboard.ui.noInternet.NoInternetScreen
import com.lirctek.loadboard.ui.offers.dialogs.AcceptOfferDetailDialog
import com.lirctek.loadboard.ui.offers.offersCommonUi.OfferItemsShimmerUi
import com.lirctek.loadboard.ui.offers.offersCommonUi.OfferItemsUi

@Composable
fun ActiveOffers(navController: NavController) {

    val viewModel = hiltViewModel<OffersActiveViewModel>()
    val status by NetworkConnectivityObserver(LocalContext.current.applicationContext).observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val state = viewModel.state
    val refreshing by viewModel.isRefreshing

    if (state.offerDataList.isEmpty() && !refreshing){
        InitShimmer(b = true)
    }else{
        InitShimmer(b = false)
    }

    var openDialog  by remember { mutableStateOf(false) }
    var dataState by remember {
        mutableStateOf(ModelState())
    }

    AcceptOfferDetailDialog(
        openDialog = dataState.data != null && openDialog,
        offerDataList = dataState.data,
        onAcceptOffer = {
            openDialog = false
        },
        onDismissOffer = {
            openDialog = false
        }
    )

    if (status != ConnectivityObserver.Status.Available && state.offerDataList.isEmpty()){
        InitShimmer(b = false)
        NoInternetScreen()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(refreshing),
            onRefresh = { viewModel.refreshItems() },
            modifier = Modifier.fillMaxSize()
        ) {
            if (state.offerDataList.isEmpty() && state.error != null){
                //Show Empty List
                InitShimmer(b = false)
                NoDataScreen("No active offers available")
            }else {
                InitShimmer(b = false)
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.offerDataList.size) { i ->
                        if (i == 0) {
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        val item = state.offerDataList[i]
                        OfferItemsUi(
                            item = item,
                            isActive = true,
                            onLayoutClick = {
                                val json = Uri.encode(Gson().toJson(it))
                                navController.navigate("main/offers/details/$json")
                            },
                            onAcceptOffer = {
                                dataState = dataState.copy(
                                    data = it
                                )
                                openDialog = true
                            },
                            onPlaceOffer = {
                            },
                            onYourOffer = {
                            }
                        )
                        if (i >= state.offerDataList.size - 1 && !state.endReached && !state.isLoading) {
                            viewModel.loadNextItems()
                        }
                        if (i == state.offerDataList.size - 1 && !state.isLoading) {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                    item {
                        if (state.isLoading && state.offerDataList.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 80.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InitShimmer(b: Boolean) {
    if (b) {
        Column {
            Spacer(modifier = Modifier.height(5.dp))
            OfferItemsShimmerUi()
            OfferItemsShimmerUi()
        }
    }
}