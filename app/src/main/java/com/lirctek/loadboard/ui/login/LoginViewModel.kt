package com.lirctek.loadboard.ui.login

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lirctek.loadboard.connectivity.ConnectivityObserver
import com.lirctek.loadboard.data.identifiers.RoleId
import com.lirctek.loadboard.data.local.Preferences
import com.lirctek.loadboard.data.reqres.DriverObject
import com.lirctek.loadboard.data.reqres.LoginRequest
import com.lirctek.loadboard.data.reqres.LoginResponse
import com.lirctek.loadboard.data.reqres.LoginResponseData
import com.lirctek.loadboard.network.NetworkResponse
import com.lirctek.loadboard.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var isValid: MutableState<Boolean> = mutableStateOf(false)
    var onSuccessFullLogin: MutableState<Boolean> = mutableStateOf(false)
    var errorMessage: MutableState<String> = mutableStateOf("")

    fun validateData(email: String, pwd: String, status: ConnectivityObserver.Status){
        if (email.isEmpty()) {
            errorMessage.value = "Please enter Username"
            isValid.value = false
        } else if (pwd.isEmpty()) {
            errorMessage.value = "Please enter Password"
            isValid.value = false
        } else if (status != ConnectivityObserver.Status.Available){
            errorMessage.value = "No Internet Connection"
            isValid.value = false
        }else{
            isValid.value = true
        }
    }

    fun loginUser(user: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(user, password)
            val response = repository.loginUser(loginRequest)
            when(response){
                is NetworkResponse.Success ->{
                    if(response.body.user != null) {
                        val loginResponse: LoginResponse = response.body
                        if(validateUser(loginResponse)) {
                            loginWithCarrier(loginResponse)
                        } else {
                            errorMessage.value = "Your Account is deactivated. Please contact safety manager or customer support."
                        }
                    }
                    else{
                        errorMessage.value = "Please Check User Name / Password"
                    }

                }
                is NetworkResponse.Error -> {
                    if (response.statusCode == 401){
                        errorMessage.value = "Please Check User Name / Password"
                    }else {
                        errorMessage.value = "Unable To Get Data Place Try Again"
                    }

                }
            }
        }
    }

    private fun validateUser(loginResponse: LoginResponse):Boolean{
        if(loginResponse.IsDeleted == 0  && loginResponse.user != null && loginResponse.user!!.Role_Id != 0 &&
            RoleId.fromInt(loginResponse.user!!.Role_Id) == RoleId.fromInt(17) && loginResponse.token != null){
            Preferences.getAppPref().token = loginResponse.token
            return true
        }
        else return  false
    }

    suspend fun loginWithCarrier(loginResponse: LoginResponse) {
        val response = repository.getUserCarrier(loginResponse.user!!.Id)
        when(response){
            is NetworkResponse.Success ->{
                try {
                    val driverObject: DriverObject = response.body
                    Log.e("LOGIN_DATA", Gson().toJson(driverObject))
                    initPreferences(loginResponse, driverObject)
                    onSuccessFullLogin.value = true

                }catch (ex:Exception){
                    Log.e("LOGIN_DATA", "EXCEPTION")
                    errorMessage.value = "Unable to get driver details, Please try again later."
                }
            }
            is NetworkResponse.Error ->{
                Log.e("LOGIN_DATA", "error")
                errorMessage.value = "Unable to get driver details, Please try again later."
            }
        }
    }

    private fun initPreferences(loginResponse: LoginResponse, driverObject: DriverObject) {
        Preferences.getAppPref().isUserLoggedIn = true
        Preferences.getAppPref().userId = loginResponse.user!!.Id
        Preferences.getAppPref().firstName = driverObject.firstName
        Preferences.getAppPref().lastName = driverObject.lastName
        Preferences.getAppPref().email = driverObject.email
        Preferences.getAppPref().driverId = driverObject.id
        Preferences.getAppPref().splitFullLoad = driverObject.splitFullLoad
        Preferences.getAppPref().isFullLoad = driverObject.isFullLoad
        Preferences.getAppPref().isLTL = driverObject.isLTL
        Preferences.getAppPref().phoneNo = driverObject.phone
        Preferences.getAppPref().gpsPingRate = driverObject.pingRate
    }

}