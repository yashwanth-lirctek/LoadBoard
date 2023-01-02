package com.lirctek.loadboard.ui.loads.inTransit

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.lirctek.loadboard.ui.loads.LoadsAvailableViewModel
import com.lirctek.loadboard.ui.loads.loadsUi.LoadsItemUi
import com.lirctek.loadboard.ui.noInternet.NoInternetScreen
import com.lirctek.loadboard.ui.offers.active.InitShimmer

@Composable
fun InTransitUi(navController: NavController) {

    val viewModel = hiltViewModel<LoadsInTransitViewModel>()
    val status by NetworkConnectivityObserver(LocalContext.current.applicationContext).observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val state = viewModel.state

    if (state.loadsList.isNotEmpty()){
        InitShimmer(b = false)
    }else{
        InitShimmer(b = false)
    }

    val refreshing by viewModel.isRefreshing

    if (status != ConnectivityObserver.Status.Available && state.loadsList.isEmpty()){
        NoInternetScreen()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(refreshing),
            onRefresh = { /*----------Pending---------------*/ },
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(5.dp))
                LoadsItemUi("InTransit")
                LoadsItemUi("InTransit")
                LoadsItemUi("InTransit")
                LoadsItemUi("InTransit")
            }
        }
    }
}