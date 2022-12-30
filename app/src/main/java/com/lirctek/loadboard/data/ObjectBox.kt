package com.lirctek.loadboard.data

import android.content.Context
import com.lirctek.loadboard.data.local.MyObjectBox
import com.lirctek.loadboard.data.local.Preferences
import io.objectbox.Box
import io.objectbox.BoxStore

object  ObjectBox {

    lateinit var boxStore: BoxStore
    lateinit var preferences: Box<Preferences>
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()

        preferences = boxStore.boxFor(Preferences::class.java)

    }

    fun get(): BoxStore {
        return boxStore
    }
}