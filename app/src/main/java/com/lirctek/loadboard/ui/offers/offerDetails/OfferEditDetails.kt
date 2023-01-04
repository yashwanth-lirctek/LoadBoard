package com.lirctek.loadboard.ui.offers.offerDetails

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.dialog.CustomCalendarView
import com.lirctek.loadboard.ui.offers.active.OffersActiveViewModel
import com.lirctek.loadboard.ui.textFieldUi.myAppTextFieldColors
import com.lirctek.loadboard.ui.toolbar.OffersEditToolBar
import com.lirctek.loadboard.ui.toolbar.OffersToolBar
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerColors
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerColors
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun OfferEditUi(navController: NavHostController, offerItem: OfferDataList){

    val viewModel = hiltViewModel<OfferEditViewModel>()
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    var offerAmount by remember {
        mutableStateOf("")
    }
    var offerAmountError by remember {
        mutableStateOf(false)
    }

    var validUntil by remember {
        mutableStateOf("")
    }
    var validUntilError by remember {
        mutableStateOf(false)
    }

    var proposedPickup by remember {
        mutableStateOf("")
    }
    var proposedPickupError by remember {
        mutableStateOf(false)
    }

    var condition by remember {
        mutableStateOf("")
    }
    var conditionError by remember {
        mutableStateOf(false)
    }

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedDateError by remember {
        mutableStateOf(false)
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }

    val dateDialogStateProposedPickup = rememberMaterialDialogState()
    val dateDialogStateValidUntil = rememberMaterialDialogState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            OffersEditToolBar(offerItem)
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        MaterialDialog(
            dialogState = dateDialogStateProposedPickup,
            buttons = {
                positiveButton(text = "Ok".uppercase(), textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onTertiary
                )) {
                    proposedPickup = formattedDate
                    proposedPickupError = false
                }
                negativeButton(text = "Cancel".uppercase(),textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onTertiary
                ))
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
                allowedDateValidator = {
                    it >= LocalDate.now()
                },
                colors = DatePickerDefaults.colors(
                    headerBackgroundColor = MaterialTheme.colorScheme.surface,
                    dateActiveBackgroundColor = MaterialTheme.colorScheme.surface
                )
            ) {
                pickedDate = it
            }
        }

        MaterialDialog(
            dialogState = dateDialogStateValidUntil,
            buttons = {
                positiveButton(
                    text = "Ok".uppercase(),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    validUntil = formattedDate
                    validUntilError = false
                }
                negativeButton(
                    text = "Cancel".uppercase(),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                )
            },
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
                allowedDateValidator = {
                    it >= LocalDate.now()
                },
                colors = DatePickerDefaults.colors(
                    headerBackgroundColor = MaterialTheme.colorScheme.surface,
                    dateActiveBackgroundColor = MaterialTheme.colorScheme.surface,
                )
            ) {
                pickedDate = it
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            PreviousAndBookNowLayout(offerItem = offerItem)
            Spacer(modifier = Modifier.height(8.dp))
            Divider(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Offer Amount".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(2.dp))
            EditTextFieldWithLendingIcon(
                value = offerAmount,
                error = offerAmountError,
                singleLine = true,
                autoCorrect = false,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            ){
                offerAmount = it
                offerAmountError = false
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Valid Until".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(2.dp))
            EditTextFieldWithTralingIcon(
                value = validUntil,
                error = validUntilError,
                singleLine = true,
                autoCorrect = false,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .wrapContentWidth()
                    .clickable {
                        dateDialogStateValidUntil.show()
                    },
                onValueChanged = {
                    validUntil = it
                    validUntilError = false
                },
                onClick = {
                    dateDialogStateValidUntil.show()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(end = 10.dp)
            ) {
                Text(
                    text = "Conditions".uppercase(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                )
                androidx.compose.material.Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = 2.dp,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .clickable {
                            if (condition.isEmpty()){
                                conditionError = true
                                return@clickable
                            }
                            if (proposedPickup.isEmpty()){
                                proposedPickupError = true
                                return@clickable
                            }
                            viewModel.addCondition(condition, proposedPickup)
                    }
                ) {
                    Text(
                        text = "Add Condition".uppercase(),
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 6.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Condition".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            )
            EditTextFieldWithNoIcon(
                value = condition,
                error = conditionError,
                singleLine = false,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                condition = it
                conditionError = false
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Proposed Pickup".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            )
            EditTextFieldWithTralingIcon(
                value = proposedPickup,
                error = proposedPickupError,
                singleLine = true,
                autoCorrect = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        dateDialogStateProposedPickup.show()
                    },
                onValueChanged = {
                    proposedPickup = it
                    proposedPickupError = false
                },
                onClick = {
                    dateDialogStateProposedPickup.show()
                }
            )

        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditTextFieldWithSpinner(onSelect: (value: String) -> Unit) {
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

    androidx.compose.material.ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        TextField(
            value = selectedOptionText,
            readOnly = true,
            onValueChange = {
                onSelect(it)
                selectedOptionText = it
            },
            modifier = Modifier.fillMaxWidth(),
            colors = myAppTextFieldColors(),
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.tertiaryContainer
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        androidx.compose.material.DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            documentsList.forEach { entry ->
                androidx.compose.material.DropdownMenuItem(
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
fun PreviousAndBookNowLayout(
    offerItem: OfferDataList
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Previous Offer".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "$ ${offerItem.offeredAmount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Book Now".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "$ ${offerItem.bookNowAmount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextFieldWithLendingIcon(
    value: String,
    error: Boolean,
    singleLine: Boolean,
    autoCorrect: Boolean,
    modifier: Modifier,
    onValueChanged: (value: String) -> Unit
){
    TextField(
        value = value,
        modifier = modifier.wrapContentWidth(),
        onValueChange = {
            if (it.length <= 5) onValueChanged(it)
        },
        colors = myAppTextFieldColors(),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiaryContainer
        ),
        singleLine = singleLine,
        isError = error,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.AttachMoney, contentDescription = "Doller")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            autoCorrect = autoCorrect
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextFieldWithTralingIcon(
    value: String,
    error: Boolean,
    singleLine: Boolean,
    autoCorrect: Boolean,
    modifier: Modifier,
    onValueChanged: (value: String) -> Unit,
    onClick: () -> Unit
){
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = {
            onValueChanged(it)
        },
        colors = myAppTextFieldColors(),
        readOnly = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiaryContainer
        ),
        singleLine = singleLine,
        isError = error,
        trailingIcon = {
            IconButton(onClick = onClick) {
                Icon(imageVector = Icons.Filled.CalendarToday, contentDescription = "Calander")
            }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextFieldWithNoIcon(
    value: String,
    error: Boolean,
    singleLine: Boolean,
    autoCorrect: Boolean,
    modifier: Modifier,
    onValueChanged: (value: String) -> Unit
){
    TextField(
        value = value,
        modifier = modifier,
        onValueChange = {
            onValueChanged(it)
        },
        colors = myAppTextFieldColors(),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiaryContainer
        ),
        singleLine = singleLine,
        isError = error,
        maxLines = 5,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = autoCorrect,
            imeAction = ImeAction.Next
        )
    )
}
