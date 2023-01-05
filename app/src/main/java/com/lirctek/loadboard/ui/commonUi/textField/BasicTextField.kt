package com.lirctek.loadboard.ui.commonUi.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun BasicTextFieldUI(
    value: String,
    placeHolder: String? = null,
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

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            onValueChanged(newText)
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiaryContainer
        ),
        readOnly = readOnly,
        singleLine = singleLine,
        decorationBox = { innerTextField ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(size = 6.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null){
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = imageDescription,
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(width = 8.dp))
                        Box {
                            if (value.isEmpty() && placeHolder != null) {
                                Text(
                                    text = placeHolder,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.LightGray
                                )
                            }
                            innerTextField()
                        }
                    } else if (trailingIcon != null){
                        Box(
                            modifier = Modifier.fillMaxWidth(0.8f)
                        ) {
                            if (value.isEmpty() && placeHolder != null) {
                                Text(
                                    text = placeHolder,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.LightGray
                                )
                            }
                            innerTextField()
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(1f),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            IconButton(
                                onClick = {
                                    onIconClick()
                                }
                            ) {
                                Icon(
                                    imageVector = trailingIcon,
                                    contentDescription = imageDescription,
                                    tint = Color.Gray
                                )
                            }
                        }

                    } else {
                        Box {
                            if (value.isEmpty() && placeHolder != null) {
                                Text(
                                    text = placeHolder,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.LightGray
                                )
                            }
                            innerTextField()
                        }
                    }
                }
                Divider(
                    thickness = 1.dp,
                    color = if (isError) Color.Red else MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            autoCorrect = autoCorrect,
            imeAction = ImeAction.Next
        ),
    )
}

//BasicTextFieldUI(
//value = userName,
//placeHolder = "User Name",
//singleLine = true,
//autoCorrect = true,
//isError = false,
//readOnly = false,
//trailingIcon = null,
//leadingIcon = Icons.Outlined.EscalatorWarning,
//imageDescription = "User Name",
//keyboardType = KeyboardType.Text,
//modifier = Modifier.padding(40.dp).fillMaxWidth(),
//onValueChanged = { userName = it },
//onIconClick = {}
//)