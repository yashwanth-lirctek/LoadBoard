package com.lirctek.loadboard.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.lirctek.loadboard.connectivity.ConnectivityObserver
import com.lirctek.loadboard.connectivity.NetworkConnectivityObserver
import com.lirctek.loadboard.ui.navigation.HomeNavigation
import com.lirctek.loadboard.ui.toolbar.HomeToolBar

private lateinit var connectivityObserver: ConnectivityObserver

@Composable
fun HomeUi(navController: NavController) {

    connectivityObserver = NetworkConnectivityObserver(LocalContext.current.applicationContext)
    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val scaffoldState = rememberScaffoldState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeToolBar(navController)
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
    }
}