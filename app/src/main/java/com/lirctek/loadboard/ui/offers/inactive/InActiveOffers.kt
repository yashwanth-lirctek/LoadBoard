package com.lirctek.loadboard.ui.offers.inactive

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lirctek.loadboard.connectivity.ConnectivityObserver
import com.lirctek.loadboard.connectivity.NetworkConnectivityObserver
import com.lirctek.loadboard.ui.noData.NoDataScreen
import com.lirctek.loadboard.ui.noInternet.NoInternetScreen
import com.lirctek.loadboard.ui.offers.active.InitShimmer
import com.lirctek.loadboard.ui.offers.offersCommonUi.OfferItemsShimmerUi
import com.lirctek.loadboard.ui.offers.offersCommonUi.OfferItemsUi

@Composable
fun InActiveOffers(navController: NavController) {

    val viewModel = hiltViewModel<OffersInActiveViewModel>()
    val status by NetworkConnectivityObserver(LocalContext.current.applicationContext).observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val state = viewModel.state

    if (state.offerDataList.isNotEmpty()){
        InitShimmer(b = false)
    }else{
        InitShimmer(b = true)
    }

    val refreshing by viewModel.isRefreshing

    if (status != ConnectivityObserver.Status.Available && state.offerDataList.isEmpty()){
        NoInternetScreen()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(refreshing),
            onRefresh = { viewModel.refreshItems("InActive") },
        ) {
            if (state.offerDataList.isEmpty() && state.error != null){
                //Show Empty List
                NoDataScreen("No in-active offers available")
            }else {
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
                                navController.navigate("main/offers/details")
                            },
                            onAcceptOffer = {},
                            onPlaceOffer = {},
                            onYourOffer = {}
                        )
                        if (i >= state.offerDataList.size - 1 && !state.endReached && !state.isLoading) {
                            viewModel.loadNextItems("InActive")
                        }
                        if (i == state.offerDataList.size - 1) {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                    item {
                        if (state.isLoading && state.offerDataList.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
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

var isFirstTimeShimmer: Boolean = true
@Composable
fun InitShimmer(b: Boolean) {
    if (b && isFirstTimeShimmer) {
        isFirstTimeShimmer = false
        Column {
            OfferItemsShimmerUi()
            OfferItemsShimmerUi()
            OfferItemsShimmerUi()
            OfferItemsShimmerUi()
        }
    }
}