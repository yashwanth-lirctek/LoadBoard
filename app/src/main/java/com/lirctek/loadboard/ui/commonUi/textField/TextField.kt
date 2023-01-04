package com.lirctek.loadboard.ui.commonUi.textField

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldUI(
    value: String,
    singleLine : Boolean = false,
    autoCorrect : Boolean = true,
    isError: Boolean = false,
    readOnly: Boolean = false,
    trailingIcon: ImageVector? = null,
    leadingIcon : ImageVector? = null,
    imageDescription: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier,
    onValueChanged: (value: String) -> Unit,
    onIconClick: () -> Unit
){
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = {
            onValueChanged(it)
        },
        colors = textFieldColors(),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiaryContainer
        ),
        readOnly = readOnly,
        singleLine = singleLine,
        isError = isError,
        maxLines = 5,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            autoCorrect = autoCorrect,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            trailingIcon?.let {
                IconButton(
                    onClick = {
                        onIconClick()
                    }
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = imageDescription
                    )
                }
            }
        },
        leadingIcon = {
            leadingIcon?.let {
                IconButton(
                    onClick = {
                        onIconClick()
                    }
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = imageDescription
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = MaterialTheme.colorScheme.tertiaryContainer,
    disabledTextColor = MaterialTheme.colorScheme.onSecondary,
    cursorColor = MaterialTheme.colorScheme.onSecondary,
    containerColor = Color.Transparent,
    unfocusedTrailingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    focusedTrailingIconColor = MaterialTheme.colorScheme.onSecondary,
    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    focusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
    focusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
    unfocusedIndicatorColor = MaterialTheme.colorScheme.onTertiaryContainer,
    errorIndicatorColor = Color.Red,
    errorLeadingIconColor = Color.Red,
    errorTrailingIconColor =Color.Red
)