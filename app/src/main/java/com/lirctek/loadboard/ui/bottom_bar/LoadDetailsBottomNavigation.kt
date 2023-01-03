package com.lirctek.loadboard.ui.bottom_bar

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.ui.theme.ProgressBarWhiteColor

@Composable
fun LoadDetailsBottomNavigation(loadData: LoadsList, navController: NavHostController){

    var currentSelectedScreenId by remember {
        mutableStateOf(0)
    }

    val items = listOf(
        LoadDetailsBottomItems.LoadDetails,
        LoadDetailsBottomItems.Documents
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = 2.dp,
        modifier = Modifier.height(70.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().height(70.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadDetailsBottomItems.LoadDetails.let { offers ->
                NavItem(
                    item = offers,
                    isSelected = offers.id == currentSelectedScreenId
                ) {
                    if (currentSelectedScreenId != offers.id) {
                        currentSelectedScreenId = offers.id
                        val json = Uri.encode(Gson().toJson(loadData))
                        navController.navigate("main/loads/details/main/$json")
                    }
                }
            }
            LoadDetailsBottomItems.Documents.let { documents ->
                NavItem(
                    item = documents,
                    isSelected = documents.id == currentSelectedScreenId
                ) {
                    if (currentSelectedScreenId != documents.id) {
                        currentSelectedScreenId = documents.id
                        val json = Uri.encode(Gson().toJson(loadData))
                        navController.navigate("main/loads/details/documents/$json")
                    }
                }
            }
        }
    }
}