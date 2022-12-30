package com.lirctek.loadboard.data.reqres

import com.google.gson.JsonObject
import com.lirctek.loadboard.data.local.Preferences

data class OffersRequest(
    var pageIndex: Int = 0,
    var pageSize: Int = 0,
    var userId: Int = Preferences.getAppPref().userId,
    var sortExpression: String = "PickupFromDate",
    var sortDirection: String = "ASC",
    var status: String? = null
)

class OffersResponse: java.io.Serializable {
    var data: List<OfferDataList> = ArrayList()
    var totalRecords: Int = 0
}

class OfferDataList{
    var id: Int = 0
    var loadNumber: String? = null
    var loadType: String? = null
    var customerName: String? = null
    var customerPhone: String? = null
    var bookNowAmount: Double = 0.0
    var pickup: String? = null
    var delivery: String? = null
    var equipment: String? = null
    var pickupFromDate: String? = null
    var deliveryFromDate: String? = null
    var fmDate : String? = null
    var fmTime : String? = null
    var stopsCount: Int = 0
    var totalMiles : Double = 0.0
    var lowestOfferAmount: Double = 0.0
    var Offer_Id: Int = 0
    var offeredAmount: Double = 0.0
    var counterOfferAmount: Double = 0.0
    var totalOffers: Int = 0
}