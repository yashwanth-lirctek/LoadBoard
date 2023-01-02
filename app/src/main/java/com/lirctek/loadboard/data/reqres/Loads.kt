package com.lirctek.loadboard.data.reqres

import android.os.Parcel
import android.os.Parcelable

data class LoadsRequest(
    var carrier_Id: Int = 0,
    var role_Id: Int = 0,
    var type: String = "Loads",
    var pageSize: Int = 0,
    var indexNumber: Int = 0
)

class LoadsList() : Parcelable {
    var woId: Int = 0
    var id: Int = 0
    var relayId: Int = 0
    var dispatchNumber: String? = null
    var dispatchStatus: Int = 0
    var carrierName: String? = null
    var truckNumber: String? = null
    var trailerNumber: String? = null
    var driver1Name: String? = null
    var dispatchOrder_Id: Int = 0
    var loadType: String? = null
    var stopDetails: List<StopDetails> = ArrayList()

    var isBOLReceived: Int = 0
    var isBOLRequired: Int = 0
    var bolReceivedDate: String? = null
    var driverViewedOn: String? = null
    var pickupDate: String? = null
    var pickupLocation: String? = null
    var deliveryDate: String? = null
    var deliveryLocation: String? = null
    var note: String? = null
    var rateDetails: List<RateDetails> = ArrayList()
    var dispatchFee: Double = 0.0
    var acceptRejectLog: List<AcceptRejectLog> = ArrayList()
    var isAccepted: Int = 0
    var isRejected: Int = 0
    var temperature: String? = null
    var totalAmount: Double = 0.0

    constructor(parcel: Parcel) : this() {
        woId = parcel.readInt()
        id = parcel.readInt()
        relayId = parcel.readInt()
        dispatchNumber = parcel.readString()
        dispatchStatus = parcel.readInt()
        carrierName = parcel.readString()
        truckNumber = parcel.readString()
        trailerNumber = parcel.readString()
        driver1Name = parcel.readString()
        dispatchOrder_Id = parcel.readInt()
        loadType = parcel.readString()
        stopDetails = parcel.createTypedArrayList(StopDetails)!!
        isBOLReceived = parcel.readInt()
        isBOLRequired = parcel.readInt()
        bolReceivedDate = parcel.readString()
        driverViewedOn = parcel.readString()
        pickupDate = parcel.readString()
        pickupLocation = parcel.readString()
        deliveryDate = parcel.readString()
        deliveryLocation = parcel.readString()
        note = parcel.readString()
        rateDetails = parcel.createTypedArrayList(RateDetails)!!
        dispatchFee = parcel.readDouble()
        acceptRejectLog = parcel.createTypedArrayList(AcceptRejectLog)!!
        isAccepted = parcel.readInt()
        isRejected = parcel.readInt()
        temperature = parcel.readString()
        totalAmount = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(woId)
        parcel.writeInt(id)
        parcel.writeInt(relayId)
        parcel.writeString(dispatchNumber)
        parcel.writeInt(dispatchStatus)
        parcel.writeString(carrierName)
        parcel.writeString(truckNumber)
        parcel.writeString(trailerNumber)
        parcel.writeString(driver1Name)
        parcel.writeInt(dispatchOrder_Id)
        parcel.writeString(loadType)
        parcel.writeTypedList(stopDetails)
        parcel.writeInt(isBOLReceived)
        parcel.writeInt(isBOLRequired)
        parcel.writeString(bolReceivedDate)
        parcel.writeString(driverViewedOn)
        parcel.writeString(pickupDate)
        parcel.writeString(pickupLocation)
        parcel.writeString(deliveryDate)
        parcel.writeString(deliveryLocation)
        parcel.writeString(note)
        parcel.writeTypedList(rateDetails)
        parcel.writeDouble(dispatchFee)
        parcel.writeTypedList(acceptRejectLog)
        parcel.writeInt(isAccepted)
        parcel.writeInt(isRejected)
        parcel.writeString(temperature)
        parcel.writeDouble(totalAmount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoadsList> {
        override fun createFromParcel(parcel: Parcel): LoadsList {
            return LoadsList(parcel)
        }

        override fun newArray(size: Int): Array<LoadsList?> {
            return arrayOfNulls(size)
        }
    }
}

class StopDetails() : Parcelable {
    var workOrderId: Int = 0
    var woStop_Id: Int = 0
    var id: Int = 0
    var stopNumber: String? = null
    var stopType: String? = null
    var fromDate: String? = null
    var fromTime: String? = null
    var address1: String? = null
    var dosStopNumber: String? = null
    var address2: String? = null
    var poNumber: String? = null
    var appNumber: String? = null
    var sealNumber: String? = null
    var toDate: String? = null
    var toTime: String? = null
    var facilityName: String? = null
    var city: String? = null
    var state: String? = null
    var zip: String? = null
    var latitude: String? = null
    var longitude: String? = null
    var arrivedDate: String? = null
    var loadedORUnLoadedDate: String? = null
    var phoneNo: String? = null
    var phoneExt: String? = null
    var notes: String? = null
    var delayReason: String? = null
    var eta: String? = null
    var checkInTime: String? = null
    var checkInLatitude: String? = null
    var checkInLongitude: String? = null
    var checkOutTime: String? = null
    var checkOutLatitude: String? = null
    var checkOutLongitude: String? = null
    var temperatureDateTime: String? = null
    var trailerTemperature: String? = null
    var temperatureDoc: String? = null
    var scaleWeight: String? = null
    var scaleDateTime: String? = null
    var scaleDoc: String? = null
    var bolNumPages: String? = null
    var bolDateTime: String? = null
    var bolDoc: String? = null
    var sealNum: String? = null
    var sealDateTime: String? = null
    var sealDoc: String? = null
    var lumper: String? = null
    var lumperNotes: String? = null
    var lumperIssuedDate: String? = null
    var lumperAmount: Int = 0
    var stopItems: java.util.ArrayList<StopItems> = ArrayList()

    constructor(parcel: Parcel) : this() {
        workOrderId = parcel.readInt()
        woStop_Id = parcel.readInt()
        id = parcel.readInt()
        stopNumber = parcel.readString()
        stopType = parcel.readString()
        fromDate = parcel.readString()
        fromTime = parcel.readString()
        address1 = parcel.readString()
        dosStopNumber = parcel.readString()
        address2 = parcel.readString()
        poNumber = parcel.readString()
        appNumber = parcel.readString()
        sealNumber = parcel.readString()
        toDate = parcel.readString()
        toTime = parcel.readString()
        facilityName = parcel.readString()
        city = parcel.readString()
        state = parcel.readString()
        zip = parcel.readString()
        latitude = parcel.readString()
        longitude = parcel.readString()
        arrivedDate = parcel.readString()
        loadedORUnLoadedDate = parcel.readString()
        phoneNo = parcel.readString()
        phoneExt = parcel.readString()
        notes = parcel.readString()
        delayReason = parcel.readString()
        eta = parcel.readString()
        checkInTime = parcel.readString()
        checkInLatitude = parcel.readString()
        checkInLongitude = parcel.readString()
        checkOutTime = parcel.readString()
        checkOutLatitude = parcel.readString()
        checkOutLongitude = parcel.readString()
        temperatureDateTime = parcel.readString()
        trailerTemperature = parcel.readString()
        temperatureDoc = parcel.readString()
        scaleWeight = parcel.readString()
        scaleDateTime = parcel.readString()
        scaleDoc = parcel.readString()
        bolNumPages = parcel.readString()
        bolDateTime = parcel.readString()
        bolDoc = parcel.readString()
        sealNum = parcel.readString()
        sealDateTime = parcel.readString()
        sealDoc = parcel.readString()
        lumper = parcel.readString()
        lumperNotes = parcel.readString()
        lumperIssuedDate = parcel.readString()
        lumperAmount = parcel.readInt()
        stopItems = parcel.createTypedArrayList(StopItems)!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(workOrderId)
        parcel.writeInt(woStop_Id)
        parcel.writeInt(id)
        parcel.writeString(stopNumber)
        parcel.writeString(stopType)
        parcel.writeString(fromDate)
        parcel.writeString(fromTime)
        parcel.writeString(address1)
        parcel.writeString(dosStopNumber)
        parcel.writeString(address2)
        parcel.writeString(poNumber)
        parcel.writeString(appNumber)
        parcel.writeString(sealNumber)
        parcel.writeString(toDate)
        parcel.writeString(toTime)
        parcel.writeString(facilityName)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(zip)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(arrivedDate)
        parcel.writeString(loadedORUnLoadedDate)
        parcel.writeString(phoneNo)
        parcel.writeString(phoneExt)
        parcel.writeString(notes)
        parcel.writeString(delayReason)
        parcel.writeString(eta)
        parcel.writeString(checkInTime)
        parcel.writeString(checkInLatitude)
        parcel.writeString(checkInLongitude)
        parcel.writeString(checkOutTime)
        parcel.writeString(checkOutLatitude)
        parcel.writeString(checkOutLongitude)
        parcel.writeString(temperatureDateTime)
        parcel.writeString(trailerTemperature)
        parcel.writeString(temperatureDoc)
        parcel.writeString(scaleWeight)
        parcel.writeString(scaleDateTime)
        parcel.writeString(scaleDoc)
        parcel.writeString(bolNumPages)
        parcel.writeString(bolDateTime)
        parcel.writeString(bolDoc)
        parcel.writeString(sealNum)
        parcel.writeString(sealDateTime)
        parcel.writeString(sealDoc)
        parcel.writeString(lumper)
        parcel.writeString(lumperNotes)
        parcel.writeString(lumperIssuedDate)
        parcel.writeInt(lumperAmount)
        parcel.writeTypedList(stopItems)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StopDetails> {
        override fun createFromParcel(parcel: Parcel): StopDetails {
            return StopDetails(parcel)
        }

        override fun newArray(size: Int): Array<StopDetails?> {
            return arrayOfNulls(size)
        }
    }
}

class RateDetails() : Parcelable {
    var Description: String? = null
    var Units: Int = 0
    var Per: Int = 0
    var StopItems: Int = 0

    constructor(parcel: Parcel) : this() {
        Description = parcel.readString()
        Units = parcel.readInt()
        Per = parcel.readInt()
        StopItems = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Description)
        parcel.writeInt(Units)
        parcel.writeInt(Per)
        parcel.writeInt(StopItems)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RateDetails> {
        override fun createFromParcel(parcel: Parcel): RateDetails {
            return RateDetails(parcel)
        }

        override fun newArray(size: Int): Array<RateDetails?> {
            return arrayOfNulls(size)
        }
    }
}

class StopItems() : Parcelable {
    var id: Int? = null
    var woStop_Id: Int? = null
    var itemNumber: String? = null
    var commodity: String? = null
    var weight: String? = null
    var length: Int? = null
    var pallets: Int? = null
    var pieceCount: Int? = null
    var poNumber: String? = null
    var coNumber: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        woStop_Id = parcel.readValue(Int::class.java.classLoader) as? Int
        itemNumber = parcel.readString()
        commodity = parcel.readString()
        weight = parcel.readString()
        length = parcel.readValue(Int::class.java.classLoader) as? Int
        pallets = parcel.readValue(Int::class.java.classLoader) as? Int
        pieceCount = parcel.readValue(Int::class.java.classLoader) as? Int
        poNumber = parcel.readString()
        coNumber = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(woStop_Id)
        parcel.writeString(itemNumber)
        parcel.writeString(commodity)
        parcel.writeString(weight)
        parcel.writeValue(length)
        parcel.writeValue(pallets)
        parcel.writeValue(pieceCount)
        parcel.writeString(poNumber)
        parcel.writeString(coNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StopItems> {
        override fun createFromParcel(parcel: Parcel): StopItems {
            return StopItems(parcel)
        }

        override fun newArray(size: Int): Array<StopItems?> {
            return arrayOfNulls(size)
        }
    }
}

class AcceptRejectLog() : Parcelable {
    var status: String? = null
    var logDateTime: String? = null
    var reason: String? = null
    var expectedRate: Int = 0

    constructor(parcel: Parcel) : this() {
        status = parcel.readString()
        logDateTime = parcel.readString()
        reason = parcel.readString()
        expectedRate = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeString(logDateTime)
        parcel.writeString(reason)
        parcel.writeInt(expectedRate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AcceptRejectLog> {
        override fun createFromParcel(parcel: Parcel): AcceptRejectLog {
            return AcceptRejectLog(parcel)
        }

        override fun newArray(size: Int): Array<AcceptRejectLog?> {
            return arrayOfNulls(size)
        }
    }
}