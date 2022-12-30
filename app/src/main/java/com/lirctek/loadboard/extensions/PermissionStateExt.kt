package com.lirctek.loadboard.extensions

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@ExperimentalPermissionsApi
fun PermissionState.isPermanentlyDenied(): Boolean{
    return !status.isGranted && !status.shouldShowRationale
}