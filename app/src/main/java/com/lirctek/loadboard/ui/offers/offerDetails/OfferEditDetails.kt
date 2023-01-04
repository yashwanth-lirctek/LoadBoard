package com.lirctek.loadboard.ui.offers.offerDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lirctek.loadboard.data.reqres.DescriptionRequest
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.commonUi.floatingButtons.ExtendedFloatingActionButtonUI
import com.lirctek.loadboard.ui.commonUi.floatingButtons.FloatingActionButtonUI
import com.lirctek.loadboard.ui.commonUi.textField.TextFieldUI
import com.lirctek.loadboard.ui.commonUi.textField.textFieldColors
import com.lirctek.loadboard.ui.dialog.AddDescriptionDialog
import com.lirctek.loadboard.ui.splash.textFieldUi.myAppTextFieldColors
import com.lirctek.loadboard.ui.toolbar.OffersEditToolBar
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun OfferEditUi(navController: NavHostController, offerItem: OfferDataList){

    val viewModel = hiltViewModel<OfferEditViewModel>()
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val descriptionList = viewModel.mGetDescription.value

    var descriptions = ArrayList<String>()
    descriptions.add("Select Description")
    descriptions.add("Testing")
//    descriptionList?.forEach { item ->
//        descriptions.add(item.Name!!)
//    }

    var showDialog by remember { mutableStateOf(false) }

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
        mutableStateOf(descriptions[0])
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

    var name by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val dateDialogStateProposedPickup = rememberMaterialDialogState()
    val dateDialogStateValidUntil = rememberMaterialDialogState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            OffersEditToolBar(offerItem)
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButtonUI(
                text = "Add Description",
                imageVector = Icons.Filled.Add,
                imageDescription = "Add Description",
                onClickExtendedFloatingActionButton = {
                    showDialog = true
                }
            )
        }
    ) { paddingValues ->

        if(showDialog)
            AddDescriptionDialog(
                name = name,
                description = description,
                setShowDialog = {
                    showDialog = it
                },
                onNameChanged = {name = it},
                onDescriptionChanged = {description = it},
                onClick = {name, description ->
                    val descriptionRequest = DescriptionRequest()
                    descriptionRequest.Name = name
                    descriptionRequest.Description = description
                    viewModel.addDescription(descriptionRequest)
                }
            )

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
            TextFieldUI(
                value = offerAmount,
                singleLine = true,
                autoCorrect = false,
                isError = offerAmountError,
                readOnly = false,
                trailingIcon = null,
                leadingIcon = Icons.Filled.AttachMoney,
                imageDescription = "Offer Amount",
                keyboardType = KeyboardType.Number,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .wrapContentWidth(),
                onValueChanged = {
                    offerAmount = it
                    offerAmountError = false
                },
                onIconClick = {}
            )

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
            TextFieldUI(
                value = validUntil,
                singleLine = true,
                autoCorrect = false,
                isError = validUntilError,
                readOnly = false,
                trailingIcon = Icons.Filled.CalendarToday,
                leadingIcon = null,
                imageDescription = "Valid Until",
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
                onIconClick = {
                    dateDialogStateValidUntil.show()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
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

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
//                TextFieldUI(
//                    value = condition,
//                    singleLine = false,
//                    autoCorrect = true,
//                    isError = conditionError,
//                    readOnly = true,
//                    trailingIcon = Icons.Filled.ArrowDropDown,
//                    leadingIcon = null,
//                    imageDescription = "Condition",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(10.dp),
//                    onValueChanged = {
//                        condition = it
//                        conditionError = false
//                    },
//                    onIconClick = {}
//                )
                
                
                TextField(
                    value = condition,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                       androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) 
                    },
                    colors = textFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                ExposedDropdownMenuBox(expanded = expanded,
                    onExpandedChange = {expanded = false}) {

                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    descriptions.forEach { entry ->
                        androidx.compose.material.DropdownMenuItem(onClick = {
                            condition = entry
                            expanded = false
                        }) {
                            Text(text = entry)
                        }
                    }
                }
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
            TextFieldUI(
                value = proposedPickup,
                singleLine = true,
                autoCorrect = false,
                isError = proposedPickupError,
                readOnly = false,
                trailingIcon = null,
                leadingIcon = null,
                imageDescription = "Proposed Pickup",
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
                onIconClick = {
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
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextField(
        value = "ywefgr",
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
        },
        colors = myAppTextFieldColors(),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiaryContainer
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = false,
            imeAction = ImeAction.Next
        )
    )
}
