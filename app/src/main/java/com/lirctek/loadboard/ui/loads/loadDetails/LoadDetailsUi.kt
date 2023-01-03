package com.lirctek.loadboard.ui.loads.loadDetails

import androidx.compose.foundation.background
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.ui.bottom_bar.LoadDetailsBottomNavigation
import com.lirctek.loadboard.ui.navigation.LoadDetailsNavigation

@Composable
fun LoadDetailsUi(mainNavController: NavHostController, loadData: LoadsList) {

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            LoadDetailsBottomNavigation(loadData = loadData, navController)
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colors.background)
        ){
            LoadDetailsNavigation(navController, mainNavController, loadData)
        }
    }
}