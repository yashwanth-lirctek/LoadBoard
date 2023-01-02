package com.lirctek.loadboard.data.reqres

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.lirctek.loadboard.data.local.Preferences
import java.io.Serializable

data class LoadBoardListRequest(
    var CustomerName: String?,
    var EquipmentType: String?,
    var FromCity: String?,
    var FromDate: String?,
    var FromState: String?,
    var LoadNumber: String?,
    var SortBy: String?,
    var SortOrder: String?,
    var ToCity: String?,
    var ToDate: String?,
    var ToState: String?,
    var User_Id: Int = Preferences.getAppPref().userId,
    var Index: Int = 0,
    var PageSize: Int = 0
)

class LoadBoardDataList() : Parcelable{
    var Id: Int = 0
    var LoadNumber: String? = null
    var LoadType: String? = null
    var CustomerName: String? = null
    var CustomerPhone: String? = null
    var BookNowAmount: Double = 0.0
    var Pickup: String? = null
    var Delivery: String? = null
    var Equipment: String? = null
    var PickupFromDate: String? = null
    var DeliveryFromDate: String? = null
    var FmDate: String? = null
    var StopsCount: Int = 0
    var TotalMiles: Int = 0
    var LowestOfferAmount: Double = 0.0
    var Offer_Id: Int = 0
    var OfferedAmount: Double = 0.0
    var FmTime: String? = null
    var TotalOffers: Int = 0
    var CounterOfferAmount: Double = 0.0

    constructor(parcel: Parcel) : this() {
        Id = parcel.readInt()
        LoadNumber = parcel.readString()
        LoadType = parcel.readString()
        CustomerName = parcel.readString()
        CustomerPhone = parcel.readString()
        BookNowAmount = parcel.readDouble()
        Pickup = parcel.readString()
        Delivery = parcel.readString()
        Equipment = parcel.readString()
        PickupFromDate = parcel.readString()
        DeliveryFromDate = parcel.readString()
        FmDate = parcel.readString()
        StopsCount = parcel.readInt()
        TotalMiles = parcel.readInt()
        LowestOfferAmount = parcel.readDouble()
        Offer_Id = parcel.readInt()
        OfferedAmount = parcel.readDouble()
        FmTime = parcel.readString()
        TotalOffers = parcel.readInt()
        CounterOfferAmount = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Id)
        parcel.writeString(LoadNumber)
        parcel.writeString(LoadType)
        parcel.writeString(CustomerName)
        parcel.writeString(CustomerPhone)
        parcel.writeDouble(BookNowAmount)
        parcel.writeString(Pickup)
        parcel.writeString(Delivery)
        parcel.writeString(Equipment)
        parcel.writeString(PickupFromDate)
        parcel.writeString(DeliveryFromDate)
        parcel.writeString(FmDate)
        parcel.writeInt(StopsCount)
        parcel.writeInt(TotalMiles)
        parcel.writeDouble(LowestOfferAmount)
        parcel.writeInt(Offer_Id)
        parcel.writeDouble(OfferedAmount)
        parcel.writeString(FmTime)
        parcel.writeInt(TotalOffers)
        parcel.writeDouble(CounterOfferAmount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoadBoardDataList> {
        override fun createFromParcel(parcel: Parcel): LoadBoardDataList {
            return LoadBoardDataList(parcel)
        }

        override fun newArray(size: Int): Array<LoadBoardDataList?> {
            return arrayOfNulls(size)
        }
    }
}