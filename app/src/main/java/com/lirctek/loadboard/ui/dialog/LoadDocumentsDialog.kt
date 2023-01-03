package com.lirctek.loadboard.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun LoadDocumentsDialog(setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    var documentType by remember {
        mutableStateOf("BOL")
    }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(5.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Select Document Type",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    CountrySelection(){
                        documentType = it
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                setValue(documentType)
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "Upload Image")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountrySelection(onSelect: (value: String) -> Unit) {
    val documentsList = listOf(
        "BOL",
        "POD",
        "Lumper Fee Receipt"
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedOptionText by remember {
        mutableStateOf(documentsList[0])
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            value = selectedOptionText,
            readOnly = true,
            onValueChange = {
                onSelect(it)
                selectedOptionText = it
            },
            label = { Text(text = "Document Type") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            documentsList.forEach { entry ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(entry)
                        selectedOptionText = entry
                        expanded = false
                    },
                ){
                    Text(text = entry)
                }
            }
        }
    }
}
