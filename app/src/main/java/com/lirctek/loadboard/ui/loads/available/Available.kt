package com.lirctek.loadboard.ui.loads.available

import android.net.Uri
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
import com.google.gson.Gson
import com.lirctek.loadboard.connectivity.ConnectivityObserver
import com.lirctek.loadboard.connectivity.NetworkConnectivityObserver
import com.lirctek.loadboard.ui.loads.LoadsConstants
import com.lirctek.loadboard.ui.loads.loadsUi.LoadsItemUi
import com.lirctek.loadboard.ui.noData.NoDataScreen
import com.lirctek.loadboard.ui.noInternet.NoInternetScreen
import com.lirctek.loadboard.ui.offers.active.InitShimmer

@Composable
fun AvailableUi(navController: NavController) {

    val viewModel = hiltViewModel<LoadsAvailableViewModel>()
    val status by NetworkConnectivityObserver(LocalContext.current.applicationContext).observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val state = viewModel.state
    val refreshing by viewModel.isRefreshing

    if (state.loadsList.isEmpty() && !refreshing){
        InitShimmer(b = true)
    }else{
        InitShimmer(b = false)
    }

    if (status != ConnectivityObserver.Status.Available && state.loadsList.isEmpty()){
        InitShimmer(b = false)
        NoInternetScreen()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(refreshing),
            onRefresh = { viewModel.refreshItems() },
            modifier = Modifier.fillMaxSize()
        ) {
            if (state.loadsList.isEmpty() && state.error != null){
                //Show Empty List
                InitShimmer(b = false)
                NoDataScreen("No active offers available")
            }else {
                InitShimmer(b = false)
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.loadsList.size) { i ->
                        if (i == 0) {
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        val item = state.loadsList[i]
                        LoadsItemUi(LoadsConstants.AVAILABLE, item){
                            val json = Uri.encode(Gson().toJson(it))
                            navController.navigate("main/loads/details/$json")
                        }
                        if (i >= state.loadsList.size - 1 && !state.endReached && !state.isLoading) {
                            viewModel.loadNextItems()
                        }
                        if (i == state.loadsList.size - 1 && !state.isLoading) {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                    item {
                        if (state.isLoading && state.loadsList.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                        top = 20.dp,
                                        bottom = 80.dp
                                    ),
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