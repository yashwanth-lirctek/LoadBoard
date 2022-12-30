package com.lirctek.loadboard

import android.app.Application
import com.lirctek.loadboard.data.ObjectBox
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LoadBoard : Application() {

    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}