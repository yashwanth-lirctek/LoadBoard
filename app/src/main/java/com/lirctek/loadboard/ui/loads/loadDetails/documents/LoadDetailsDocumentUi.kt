package com.lirctek.loadboard.ui.loads.loadDetails.documents

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.ui.commonUi.floatingButtons.FloatingActionButtonUI
import com.lirctek.loadboard.ui.dialog.LoadDocumentsDialog
import com.lirctek.loadboard.ui.toolbar.LoadDetailDocumentsToolBar

@Composable
fun LoadDetailsDocumentUi(mainNavController: NavController, loadData: LoadsList) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            LoadDetailDocumentsToolBar(loadData = loadData)
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButtonUI(
                imageVector = Icons.Filled.Add,
                imageDescription = "Add",
                onClickFloatingActionButton = {
                    showDialog = true
                }
            )
        },
        scaffoldState = scaffoldState,
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
    ) { paddingValues ->

        if(showDialog)
            LoadDocumentsDialog(setShowDialog = {
                showDialog = it
            }) {
                Log.i("HomePage","HomePage : $it")
            }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
        }
    }
}