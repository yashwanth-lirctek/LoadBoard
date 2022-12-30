package com.lirctek.loadboard.ui.dialog

import android.app.Activity
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun FinalScoreDialog(
    modifier: Modifier = Modifier
) {
    var openDialog  by remember { mutableStateOf(true) }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
            },
            title = { Text("Congrutations") },
            text = { Text("You Success") },
            modifier = modifier,
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text(text = "OK")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                }) {
                    Text(text = "AGAIN")
                }
            }
        )
    }
}