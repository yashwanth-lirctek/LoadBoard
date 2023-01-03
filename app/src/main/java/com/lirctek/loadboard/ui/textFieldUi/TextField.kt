package com.lirctek.loadboard.ui.textFieldUi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextField(
    value: String,
    label: String,
    singleLine: Boolean,
    autoCorrect: Boolean,
    modifier: Modifier,
    onValueChanged:(value: String) -> Unit
){

    var enteredText by remember {
        mutableStateOf("")
    }

    TextField(
        value = value,
        modifier = modifier,
        onValueChange = {
            enteredText = it
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
        ),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.AttachMoney, contentDescription = "Doller")
        }
    )

//    OutlinedTextField(
//        value = value,
//        onValueChange = {
//            enteredText = it
//        },
//        label = {
//            Text(
//                text = label,
//                fontFamily = fontFamily,
//                fontWeight = FontWeight.Medium
//            )
//        },
//        placeholder = {
//            Text(
//                label,
//                fontFamily = fontFamily,
//                fontWeight = FontWeight.Normal
//            )
//        },
//        textStyle = TextStyle(
//            fontSize = 18.sp,
//            fontFamily = fontFamily,
//            fontWeight = FontWeight.Medium
//        ),
//        singleLine = singleLine,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Text,
//            autoCorrect = autoCorrect
//        ),
//        modifier = modifier,
//        leadingIcon = {
//            Icon(imageVector = Icons.Filled.AttachMoney, contentDescription = "Doller")
//        }
//    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myAppTextFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = MaterialTheme.colorScheme.tertiaryContainer,
    disabledTextColor = MaterialTheme.colorScheme.onSecondary,
    cursorColor = MaterialTheme.colorScheme.onSecondary,
    containerColor = Color.Transparent,
    unfocusedTrailingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    focusedTrailingIconColor = MaterialTheme.colorScheme.onSecondary,
    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    focusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
    focusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
    unfocusedIndicatorColor = MaterialTheme.colorScheme.onTertiaryContainer
)