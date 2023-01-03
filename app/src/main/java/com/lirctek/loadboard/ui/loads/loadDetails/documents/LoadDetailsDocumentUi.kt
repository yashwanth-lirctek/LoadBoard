package com.lirctek.loadboard.ui.loads.loadDetails.documents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.ui.bottom_bar.FabButton
import com.lirctek.loadboard.ui.bottom_bar.FabButtonDocuments
import com.lirctek.loadboard.ui.dialog.LoadDocumentsDialog
import com.lirctek.loadboard.ui.toolbar.LoadDetailDocumentsToolBar
import com.lirctek.loadboard.ui.toolbar.LoadDetailMainToolBar

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
            FabButtonDocuments(){
                showDialog = true
            }
        },
        scaffoldState = scaffoldState
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
                .background(MaterialTheme.colors.background)
        ){
        }
    }
}