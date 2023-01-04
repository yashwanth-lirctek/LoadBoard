package com.lirctek.loadboard.ui.settings.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lirctek.loadboard.ui.splash.textFieldUi.OutLineTextField

@Composable
fun PasswordUi() {

    var companyName: String = ""

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
                label = "Old Password",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth()
            ){}
            Spacer(modifier = Modifier.height(10.dp))
            OutLineTextField(
                value = companyName,
                label = "New Password",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth()
            ){}
            Spacer(modifier = Modifier.height(10.dp))
            OutLineTextField(
                value = companyName,
                label = "Confirm Password",
                singleLine = true,
                autoCorrect = true,
                modifier = Modifier
                    .fillMaxWidth()
            ){}
        }
    }
}
