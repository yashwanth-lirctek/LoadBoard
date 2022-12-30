package com.lirctek.loadboard.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lirctek.loadboard.connectivity.ConnectivityObserver
import com.lirctek.loadboard.connectivity.NetworkConnectivityObserver
import com.lirctek.loadboard.ui.bottom_bar.BottomNav
import com.lirctek.loadboard.ui.bottom_bar.FabButton
import com.lirctek.loadboard.ui.navigation.HomeNavigation

private lateinit var connectivityObserver: ConnectivityObserver

@Composable
fun MainUi(mainNavController: NavController){

    connectivityObserver = NetworkConnectivityObserver(LocalContext.current.applicationContext)
    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FabButton(navController)
        },
        bottomBar = {
            BottomNav(navController)
        }
    ) { paddingValues ->
        Column {
            HomeNavigation(navController, mainNavController)
        }
    }
    
}