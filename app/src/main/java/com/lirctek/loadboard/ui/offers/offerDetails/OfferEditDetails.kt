package com.lirctek.loadboard.ui.offers.offerDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.textFieldUi.myAppTextFieldColors
import com.lirctek.loadboard.ui.toolbar.OffersToolBar

@Composable
fun OfferEditUi(navController: NavHostController, offerItem: OfferDataList){

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    var offerAmount by remember {
        mutableStateOf("")
    }

    var validUntil by remember {
        mutableStateOf("")
    }
    var proposedPickup by remember {
        mutableStateOf("")
    }

    var condition by remember {
        mutableStateOf("")
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            OffersToolBar(offerItem)
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

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
                label = "Offer Amount",
                singleLine = true,
                autoCorrect = false,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            ){
                offerAmount = it
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
                label = "Valid Until",
                singleLine = true,
                autoCorrect = false,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth().wrapContentWidth()
            ){
                validUntil = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Conditions".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Condition".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            )
            EditTextFieldWithNoIcon(
                value = condition,
                label = "Conditions".uppercase(),
                singleLine = false,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth().padding(10.dp)
            ){
                condition = it
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
                label = "Proposed Pickup".uppercase(),
                singleLine = true,
                autoCorrect = false,
                modifier = Modifier
                    .fillMaxWidth().padding(10.dp)
            ){
                proposedPickup = it
            }

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
    label: String,
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
            color = MaterialTheme.colorScheme.tertiaryContainer
        ),
        singleLine = singleLine,
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
    label: String,
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
        readOnly = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.tertiaryContainer
        ),
        singleLine = singleLine,
        trailingIcon = {
            Icon(imageVector = Icons.Filled.CalendarToday, contentDescription = "Calander")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextFieldWithNoIcon(
    value: String,
    label: String,
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
        maxLines = 5,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = autoCorrect,
            imeAction = ImeAction.Next
        )
    )
}