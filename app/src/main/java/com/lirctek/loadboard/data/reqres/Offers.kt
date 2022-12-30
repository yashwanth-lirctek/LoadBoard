package com.lirctek.loadboard.data.reqres

import android.os.Parcel
import android.os.Parcelable
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


class OfferDataList() : Parcelable {
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

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        loadNumber = parcel.readString()
        loadType = parcel.readString()
        customerName = parcel.readString()
        customerPhone = parcel.readString()
        bookNowAmount = parcel.readDouble()
        pickup = parcel.readString()
        delivery = parcel.readString()
        equipment = parcel.readString()
        pickupFromDate = parcel.readString()
        deliveryFromDate = parcel.readString()
        fmDate = parcel.readString()
        fmTime = parcel.readString()
        stopsCount = parcel.readInt()
        totalMiles = parcel.readDouble()
        lowestOfferAmount = parcel.readDouble()
        Offer_Id = parcel.readInt()
        offeredAmount = parcel.readDouble()
        counterOfferAmount = parcel.readDouble()
        totalOffers = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(loadNumber)
        parcel.writeString(loadType)
        parcel.writeString(customerName)
        parcel.writeString(customerPhone)
        parcel.writeDouble(bookNowAmount)
        parcel.writeString(pickup)
        parcel.writeString(delivery)
        parcel.writeString(equipment)
        parcel.writeString(pickupFromDate)
        parcel.writeString(deliveryFromDate)
        parcel.writeString(fmDate)
        parcel.writeString(fmTime)
        parcel.writeInt(stopsCount)
        parcel.writeDouble(totalMiles)
        parcel.writeDouble(lowestOfferAmount)
        parcel.writeInt(Offer_Id)
        parcel.writeDouble(offeredAmount)
        parcel.writeDouble(counterOfferAmount)
        parcel.writeInt(totalOffers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OfferDataList> {
        override fun createFromParcel(parcel: Parcel): OfferDataList {
            return OfferDataList(parcel)
        }

        override fun newArray(size: Int): Array<OfferDataList?> {
            return arrayOfNulls(size)
        }
    }
}