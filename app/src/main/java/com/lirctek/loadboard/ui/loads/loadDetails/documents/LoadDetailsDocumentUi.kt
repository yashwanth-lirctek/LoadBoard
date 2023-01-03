package com.lirctek.loadboard.ui.loads.loadDetails.documents

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.gson.Gson
import com.lirctek.loadboard.data.reqres.LoadsList

@Composable
fun LoadDetailsDocumentUi(mainNavController: NavController, loadsList: LoadsList) {
    Log.i("LOADS_LIST", Gson().toJson(loadsList))
}