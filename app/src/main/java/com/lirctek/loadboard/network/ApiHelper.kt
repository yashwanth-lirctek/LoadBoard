package com.lirctek.loadboard.network

import com.google.gson.JsonObject
import com.lirctek.loadboard.data.reqres.*
import retrofit2.http.*

interface ApiHelper {

    @POST(ApiEndpoint.LOGIN)
    suspend  fun loginUser(@Body request: LoginRequest): NetworkResponse<LoginResponse>

    @GET(ApiEndpoint.GET_USER_INFO_BY_DRIVER_CARRIER  + "/{userId}")
    suspend fun getUserCarrier(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Path("userId") userId: Int): NetworkResponse<DriverObject>

    @POST(ApiEndpoint.GET_OFFERS)
    suspend  fun getOffers(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: OffersRequest): NetworkResponse<OffersResponse>

    @POST(ApiEndpoint.LOAD_BOARD_LIST)
    suspend  fun getLoadBoardList(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: LoadBoardListRequest): NetworkResponse<List<LoadBoardDataList>>

    @POST(ApiEndpoint.GET_TRIP_LOAD)
    suspend  fun getTripLoadList(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: LoadsRequest): NetworkResponse<List<LoadsList>>

    @POST(ApiEndpoint.ADD_EDIT_DESCRIPTION)
    suspend  fun addEditDescription(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: DescriptionRequest): NetworkResponse<DescriptionResponse>

    @POST(ApiEndpoint.GET_DESCRIPTION)
    suspend  fun getDescription(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: DescriptionListRequest): NetworkResponse<List<DescriptionListResponse>>

    @POST(ApiEndpoint.ADD_EDIT_OFFER)
    suspend  fun submitOffer(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: AddEditOfferRequest): NetworkResponse<JsonObject>

    @POST(ApiEndpoint.GET_OFFER_DETAIL)
    suspend  fun getOfferDetails(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: OfferDetailsRequest): NetworkResponse<List<OfferDetailsResponse>>

}