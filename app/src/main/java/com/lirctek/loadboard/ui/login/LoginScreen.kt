package com.lirctek.loadboard.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.lirctek.loadboard.R
import com.lirctek.loadboard.connectivity.ConnectivityObserver
import com.lirctek.loadboard.connectivity.NetworkConnectivityObserver
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.dialog.FinalScoreDialog
import com.lirctek.loadboard.ui.loadingIndicator.DialogBoxLoading
import kotlinx.coroutines.launch

private lateinit var connectivityObserver: ConnectivityObserver

@Composable
fun LoginScreen(navController: NavController){

    connectivityObserver = NetworkConnectivityObserver(LocalContext.current.applicationContext)
    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    val scaffoldState = rememberScaffoldState()

    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val loginViewModel = hiltViewModel<LoginViewModel>()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var buttonClick by rememberSaveable { mutableStateOf(true) }
    var loadingDialog by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val isCredentialsValid = loginViewModel.isValid
    val isSuccessful = loginViewModel.onSuccessFullLogin
    val isErrorMessage = loginViewModel.errorMessage

    Scaffold(modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { paddingValues ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 30.dp)
        ) {

            Image(
                painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.logo_dark else R.drawable.logo_white),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(200.dp)
                    .height(130.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextField(value = userName,
                label = {
                    Text(
                        text = "User Name",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium
                    )
                },
                placeholder = {
                    Text(
                        "User Name",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal
                    )
                },
                onValueChange = {
                    userName = it
                },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    autoCorrect = false
                ),
                modifier = Modifier
                    .width(300.dp)
                    .background(MaterialTheme.colors.background)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = password,
                label = {
                    Text(
                        text = "Password",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium
                    )
                },
                placeholder = {
                    Text(
                        "Password",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal
                    )
                },
                onValueChange = {
                    password = it
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    autoCorrect = false
                ),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium
                ),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                },
                modifier = Modifier
                    .width(300.dp)
                    .background(MaterialTheme.colors.background)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                enabled = buttonClick,
                onClick = {
                    buttonClick = false
                    loadingDialog = true
                    loginViewModel.validateData(userName, password, status)
                }) {
                Text(
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                )
            }
        }



        if (isErrorMessage.value.isNotEmpty()){
            buttonClick = true
            loadingDialog = false
            LaunchedEffect(scaffoldState.snackbarHostState){
                scaffoldState.snackbarHostState.showSnackbar(loginViewModel.errorMessage.value)
                loginViewModel.errorMessage.value = ""
            }
        }

        if (isCredentialsValid.value){
            isCredentialsValid.value = false
            loginViewModel.loginUser(userName, password)
        }

        if (isSuccessful.value){
            isSuccessful.value = false
            buttonClick = true
            loadingDialog = false
            navController.navigate("main")
        }

        if (loadingDialog){
            DialogBoxLoading()
        }

    }
}