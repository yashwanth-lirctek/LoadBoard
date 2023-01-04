package com.lirctek.loadboard.data.reqres

import com.lirctek.loadboard.data.local.Preferences

data class DescriptionListRequest(
    var CompanyId : Int = Preferences.getAppPref().companyId,
    var Type: String = "LBOffers"
)

class DescriptionListResponse{
    var Description:String? = null
    var Company_Id : Int = 0
    var Name: String? = null
    var Id: Int = 0
}
