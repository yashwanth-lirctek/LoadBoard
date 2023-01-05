package com.lirctek.loadboard.data.reqres

data class OfferDetailsRequest(
    var Id: Int = 0
)

data class OfferDetailsResponse(
    var Id: Int? = null,
    var CustomerOrder_Id: Int? = null,
    var Carrier_Id: Long? = null,
    var CreatedUserId: Int? = null,
    var OfferedDT: String? = null,
    var OfferedAmount: Double? = null,
    var OfferValidDT: String? = null,
    var CounterOfferAmount: Double? = null,
    var CounterOfferValidDT: String? = null,
    var CounterOfferDT: String? = null,
    var CarrierAcceptedDT: String? = null,
    var Status: String? = null,
    var OfferConditions: ArrayList<ArrayConditions>? = null
)


