package com.lirctek.loadboard.data.reqres

import com.lirctek.loadboard.data.local.Preferences

data class DescriptionRequest(
    var Description:String? = null,
    var Category: String = "LBOffers",
    var Company_Id : Int = Preferences.getAppPref().companyId,
    var Name: String? = null,
    var Id: Int = 0
)

class DescriptionResponse{
    var Description:String? = null
    var Category: String? = null
    var Company_Id : Int = 0
    var Name: String? = null
    var Id: Int = 0
}