package com.lirctek.loadboard.ui.offers.dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun AcceptOfferDetailDialog(
    openDialog : Boolean,
    offerDataList: OfferDataList?,
    onAcceptOffer: (offerItem: OfferDataList?) -> Unit,
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
                "Are you sure?\nDo you want to accept offer at $ ${offerDataList?.bookNowAmount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            ) },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissOffer()
                    }
                ) {
                    Text(text = "Dismiss".uppercase(),  fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium)
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onAcceptOffer(offerDataList)
                }) {
                    Text(text = "Yes".uppercase(),  fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium)
                }
            }
        )
    }
}