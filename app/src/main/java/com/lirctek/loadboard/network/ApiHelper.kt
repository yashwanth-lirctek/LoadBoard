package com.lirctek.loadboard.network

import com.lirctek.loadboard.data.reqres.*
import retrofit2.http.*

interface ApiHelper {

    @POST(ApiEndpoint.LOGIN)
    suspend  fun loginUser(@Body request: LoginRequest): NetworkResponse<LoginResponse>

    @GET(ApiEndpoint.GET_USER_INFO_BY_DRIVER_CARRIER  + "/{userId}")
    suspend fun getUserCarrier(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Path("userId") userId: Int): NetworkResponse<DriverObject>

    @POST(ApiEndpoint.GET_OFFERS)
    suspend  fun getOffers(@Header(ApiEndpoint.AUTHORIZATION) header: String, @Body request: OffersRequest): NetworkResponse<OffersResponse>
}