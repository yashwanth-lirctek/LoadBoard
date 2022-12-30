package com.lirctek.loadboard.ui.textFieldUi

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun OutLineTextField(
    value: String,
    label: String,
    singleLine: Boolean,
    autoCorrect: Boolean,
    modifier: Modifier,
    onValueChanged:(value: String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChanged(it)
        },
        label = {
            Text(
                text = label,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        },
        placeholder = {
            Text(
                label,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            )
        },
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium
        ),
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = autoCorrect
        ),
        modifier = modifier
    )
}