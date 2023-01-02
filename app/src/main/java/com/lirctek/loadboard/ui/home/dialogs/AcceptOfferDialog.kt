package com.lirctek.loadboard.ui.home.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import com.lirctek.loadboard.data.reqres.LoadBoardDataList
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun AcceptOfferHomeDialog(
    openDialog : Boolean,
    offerDataList: LoadBoardDataList,
    onAcceptOffer: (offerItem: LoadBoardDataList) -> Unit,
    onDismissOffer: () -> Unit
){

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
            },
            title = { Text(
                "Accept Offer",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            ) },
            text = { Text(
                "Are you sure?\nDo you want to accept offer at $ ${offerDataList.BookNowAmount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            ) },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissOffer()
                    }
                ) {
                    Text(text = "Dismiss",  fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium)
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onAcceptOffer(offerDataList)
                }) {
                    Text(text = "Yes",  fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium)
                }
            }
        )
    }
}