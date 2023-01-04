package com.lirctek.loadboard.network

import com.lirctek.loadboard.data.reqres.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(ApiEndpoint.LOGIN)
    suspend  fun loginUser(@Body request: LoginRequest): Response<LoginResponse>

    @GET(ApiEndpoint.GET_USER_INFO_BY_DRIVER_CARRIER  + "/{userId}")
    suspend fun getUserCarrier(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Path("userId") userId: Int): Response<DriverObject>

    @POST(ApiEndpoint.GET_OFFERS)
    suspend fun getOffers(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: OffersRequest): Response<OffersResponse>

    @POST(ApiEndpoint.LOAD_BOARD_LIST)
    suspend fun getLoadBoardList(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: LoadBoardListRequest): Response<List<LoadBoardDataList>>

    @POST(ApiEndpoint.GET_TRIP_LOAD)
    suspend fun getTripLoadList(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: LoadsRequest): Response<List<LoadsList>>

    @POST(ApiEndpoint.ADD_EDIT_DESCRIPTION)
    suspend fun addEditDescription(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: DescriptionRequest): Response<DescriptionResponse>

    @POST(ApiEndpoint.GET_DESCRIPTION)
    suspend fun getDescription(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: DescriptionListRequest): Response<List<DescriptionListResponse>>

}