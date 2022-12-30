package com.lirctek.loadboard.ui.settings.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.textFieldUi.OutLineTextField

@Composable
fun ProfileUi(){

    var companyName : String = ""

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 5.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            OutLineTextField(
                value = companyName,
                label = "Company Name",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            ){}
            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                OutLineTextField(
                    value = companyName,
                    label = "DOT",
                    singleLine = true,
                    autoCorrect = true,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .background(MaterialTheme.colors.background)
                ){}
                Spacer(modifier = Modifier.width(10.dp))
                OutLineTextField(
                    value = companyName,
                    label = "MC",
                    singleLine = true,
                    autoCorrect = true,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .background(MaterialTheme.colors.background)
                ){}
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutLineTextField(
                value = companyName,
                label = "Email",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colors.background)
            ){}
            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                OutLineTextField(
                    value = companyName,
                    label = "Phone",
                    singleLine = true,
                    autoCorrect = true,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .background(MaterialTheme.colors.background)
                ){}
                Spacer(modifier = Modifier.width(10.dp))
                OutLineTextField(
                    value = companyName,
                    label = "Mobile",
                    singleLine = true,
                    autoCorrect = true,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .background(MaterialTheme.colors.background)
                ){}
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutLineTextField(
                value = companyName,
                label = "Contact Person",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colors.background)
            ){}
            Spacer(modifier = Modifier.height(10.dp))
            OutLineTextField(
                value = companyName,
                label = "Address 1",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colors.background)
            ){}
            Spacer(modifier = Modifier.height(10.dp))
            OutLineTextField(
                value = companyName,
                label = "Address 2",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colors.background)
            ){}
            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                OutLineTextField(
                    value = companyName,
                    label = "City",
                    singleLine = true,
                    autoCorrect = true,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .background(MaterialTheme.colors.background)
                ){}
                Spacer(modifier = Modifier.width(10.dp))
                OutLineTextField(
                    value = companyName,
                    label = "State",
                    singleLine = true,
                    autoCorrect = true,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .background(MaterialTheme.colors.background)
                ){}
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutLineTextField(
                value = companyName,
                label = "Zip",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colors.background)
            ){}
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

