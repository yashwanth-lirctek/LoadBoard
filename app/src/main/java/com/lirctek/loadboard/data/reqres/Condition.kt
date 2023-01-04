package com.lirctek.loadboard.data.reqres

data class ConditionRequest(
    var CompanyId: Int,
    var Type : String = "LBOffers"
)

class Conditions{
    var id: Int = 0
    var Name: String? = null
    var Description: String? = null
    var Company_Id: String? = null
}