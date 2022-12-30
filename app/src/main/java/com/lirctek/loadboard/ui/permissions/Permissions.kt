package com.lirctek.loadboard.ui.permissions

import android.Manifest
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.lirctek.loadboard.connectivity.ConnectivityObserver
import com.lirctek.loadboard.ui.navigation.Navigation

@ExperimentalPermissionsApi
@Composable
fun Permissions() {

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val lifeCycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifeCycleOwner, effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START){
                    permissionsState.launchMultiplePermissionRequest()
                }
            }
            lifeCycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifeCycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )


    if (permissionsState.allPermissionsGranted){
        Log.e("PERMISSIONS", "ALL GRANTED")
    }else{
        Log.e("PERMISSIONS", "ALL NOT GRANTED")
    }

    Navigation()

}