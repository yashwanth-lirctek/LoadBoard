package com.lirctek.loadboard.ui.loads.loadDetails.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.ui.bottom_bar.LoadDetailsBottomNavigation
import com.lirctek.loadboard.ui.navigation.LoadDetailsNavigation
import com.lirctek.loadboard.ui.toolbar.LoadDetailMainToolBar

@Composable
fun LoadDetailsMainUi(mainNavController: NavController, loadData: LoadsList) {

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            LoadDetailMainToolBar(loadData = loadData)
        },
        scaffoldState = scaffoldState,
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
        }
    }
}