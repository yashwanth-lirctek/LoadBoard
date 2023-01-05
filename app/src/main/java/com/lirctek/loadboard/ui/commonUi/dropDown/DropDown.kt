package com.lirctek.loadboard.ui.commonUi.dropDown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lirctek.loadboard.ui.commonUi.textField.textFieldColors

@Composable
fun DropDownWithOutLinedTextField(list: ArrayList<String>, onSelect: (value: String) -> Unit) {

    var selectedOptionText by remember {
        mutableStateOf(list[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

   ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.padding(10.dp)
    ) {
        TextField(
            value = selectedOptionText,
            readOnly = true,
            onValueChange = {
                onSelect(it)
                selectedOptionText = it
            },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = textFieldColors()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .wrapContentWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
                .requiredSizeIn(maxHeight = 250.dp)
        ) {
            list.forEach { entry ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(entry)
                        selectedOptionText = entry
                        expanded = false
                    },
                ) {
                    Text(text = entry)
                }
            }
        }
    }
}

@Composable
fun DropDownWithTextField(
    list: ArrayList<String>, label: String, onSelect: (value: String) -> Unit
){
    var selectedOptionText by remember {
        mutableStateOf(list[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.padding(10.dp)
    ) {
        OutlinedTextField(
            value = selectedOptionText,
            readOnly = true,
            onValueChange = {
                onSelect(it)
                selectedOptionText = it
            },
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = textFieldColors()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .wrapContentWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
                .requiredSizeIn(maxHeight = 250.dp)
        ) {
            list.forEach { entry ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(entry)
                        selectedOptionText = entry
                        expanded = false
                    },
                ) {
                    Text(text = entry)
                }
            }
        }
    }
}