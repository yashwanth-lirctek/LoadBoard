package com.lirctek.loadboard.repository

import android.util.Log
import com.google.gson.Gson
import com.lirctek.loadboard.data.local.Preferences
import com.lirctek.loadboard.data.reqres.*
import com.lirctek.loadboard.network.ApiHelper
import com.lirctek.loadboard.network.NetworkResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiHelper: ApiHelper
) {

    suspend fun loginUser(request: LoginRequest): NetworkResponse<LoginResponse> {
        return apiHelper.loginUser(request)
    }

    suspend fun getUserCarrier(userId: Int): NetworkResponse<DriverObject> {
        return apiHelper.getUserCarrier(getNewToken(), userId)
    }

    suspend fun getOffers(index: Int, pageSize: Int, status: String): Result<List<OfferDataList>>{
        val offersRequest = OffersRequest(
            pageIndex = index,
            pageSize = pageSize,
            status = status
        )
        val response = apiHelper.getOffers(getNewToken(), offersRequest)
        when (response) {
            is NetworkResponse.Success -> {
                if (response.body.data.isNotEmpty()) {
                    return Result.success(response.body.data)
                } else {
                    return Result.success(emptyList())
                }
            }
            is NetworkResponse.Error -> {
                return Result.failure(TestException("Unable to get data please try again"))
            }
        }
    }

    suspend fun getLoadBoardList(index: Int, pageSize: Int): Result<List<LoadBoardDataList>>{
        val loadBoardListRequest = LoadBoardListRequest(
            CustomerName = null,
            EquipmentType = null,
            FromCity = null,
            FromDate = null,
            FromState = null,
            LoadNumber = null,
            SortBy = null,
            SortOrder = null,
            ToCity = null,
            ToDate = null,
            ToState = null,
            Index = index,
            PageSize = pageSize,
        )
        val response = apiHelper.getLoadBoardList(getToken(), loadBoardListRequest)
        when (response){
            is NetworkResponse.Success -> {
                if (response.body.isNotEmpty()) {
                    return Result.success(response.body)
                } else {
                    return Result.success(emptyList())
                }
            }
            is NetworkResponse.Error -> {
                return Result.failure(TestException("Unable to get data please try again"))
            }
        }
    }

    suspend fun getTripLoadList(index: Int, pageSize: Int): Result<List<LoadsList>>{
        val loadsRequest = LoadsRequest(
            carrier_Id = Preferences.getAppPref().driverId.toInt(),
            role_Id = 17,
            pageSize = pageSize,
            indexNumber = index
        )
        val response = apiHelper.getTripLoadList(getNewToken(), loadsRequest)
        when (response){
            is NetworkResponse.Success -> {
                if (response.body.isNotEmpty()) {
                    return Result.success(response.body)
                } else {
                    return Result.success(emptyList())
                }
            }
            is NetworkResponse.Error -> {
                return Result.failure(TestException("Unable to get data please try again"))
            }
        }
    }

    fun getToken(): String {
        return ("Bearer " + Preferences.getAppPref().token)
    }

    fun getNewToken(): String {
        return "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOjIyNywiaWQiOjc0OTUsImlhdCI6MTY3MTcxMzE4MywiaXNzIjoicHJpbWUtc2VydmljZSJ9.nlOJETA9yFl5yd61nUHHFttlB54DItP5A2etMRVfnrw"
    }
}

class TestException(message:String): Exception(message)