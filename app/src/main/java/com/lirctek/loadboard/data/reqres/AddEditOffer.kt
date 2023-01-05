package com.lirctek.loadboard.data.reqres

data class AddEditOfferRequest(
    var Id: Long? = null,
    var CustomerOrder_Id: Int? = null,
    var Carrier_Id: Int? = null,
    var CreatedUserId: Int? = null,
    var OfferedAmount: Long? = null,
    var OfferValidDT: String? = null,
    var Status: String? = null,
    var Conditions: ArrayList<ArrayConditions>? = null
)

data class ArrayConditions(
    var Description_Id: Int? = null,
    var DescriptionName: String? = null,
    var PickupDT: String? = null,
    var CreatedUserId: Int? = null,
    var Comments: String? = null,
    var Status: String? = null
)
