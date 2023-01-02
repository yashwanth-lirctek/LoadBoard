package com.lirctek.loadboard.data.local

import com.lirctek.loadboard.data.ObjectBox
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Entity
class Preferences {

    @Id
    var id: Long = 0

    var isUserLoggedIn: Boolean = false
        set(value){
            field = value
            insert(this)
        }

    var token: String? = null
        set(value){
            field = value
            insert(this)
        }

    var userId: Int = 0
        set(value){
            field = value
            insert(this)
        }

    var firstName: String? = null
        set(value){
            field = value
            insert(this)
        }

    var lastName: String? = null
        set(value){
            field = value
            insert(this)
        }

    var email: String? = null
        set(value){
            field = value
            insert(this)
        }

    var driverId: Long = 0
        set(value){
            field = value
            insert(this)
        }

    var splitFullLoad: Int = 0
        set(value){
            field = value
            insert(this)
        }

    var isFullLoad: Int = 0
        set(value){
            field = value
            insert(this)
        }

    var isLTL: Int = 0
        set(value){
            field = value
            insert(this)
        }

    var phoneNo: String? = null
        set(value){
            field = value
            insert(this)
        }

    fun onLogout() {
        isUserLoggedIn = false
        token = null
        userId = 0
        firstName = null
        lastName = null
        email = null
        driverId = 0
        splitFullLoad = 0
        isFullLoad = 0
        isLTL = 0
        phoneNo = null
    }

    fun insert(preferences: Preferences){
        MainScope().launch (Dispatchers.IO) {
            preferences.id = ObjectBox.preferences.put(preferences)
        }
    }

    companion object{

        private val preferenceData  : Preferences by lazy {
            var preferences: Preferences? = ObjectBox.preferences.query().build().findFirst()
            if (preferences == null) {
                preferences = Preferences()
            }
            return@lazy preferences
        }

        fun getAppPref(): Preferences {
            return preferenceData
        }

    }

}