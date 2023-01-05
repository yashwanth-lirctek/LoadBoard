package com.lirctek.loadboard.network

import com.google.gson.JsonObject
import com.lirctek.loadboard.data.reqres.*
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    @BASEURL private val apiService: ApiService,
    @BASEURLELD private val apiServiceEld: ApiService) : ApiHelper {

    private suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): NetworkResponse<T> {
        return try {
            NetworkResponse.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            NetworkResponse.Error(throwable)
        }
    }

    private suspend fun <T : Any> safeApiCallTwo(apiCall: suspend () -> Response<T>): NetworkResponse<T> {
        return try {
            val response = apiCall.invoke() as Response<*>
            if(response.code()==200 && response.body() != null ){
                return  NetworkResponse.Success(response.body() as T)
            }
            else{
                return NetworkResponse.Error(Throwable("Error"),response.code())
            }

        } catch (throwable: Throwable) {
            NetworkResponse.Error(throwable,500)
        }
    }

    override suspend fun loginUser(request: LoginRequest): NetworkResponse<LoginResponse> {
        return safeApiCallTwo { apiService.loginUser(request) }
    }

    override suspend fun getUserCarrier(header: String, userId: Int): NetworkResponse<DriverObject> {
        return safeApiCall { apiServiceEld.getUserCarrier(header, userId).body()!!}
    }

    override suspend fun getOffers(header: String, request: OffersRequest): NetworkResponse<OffersResponse> {
        return safeApiCall { apiServiceEld.getOffers(header, request).body()!!}
    }

    override suspend fun getLoadBoardList(header: String, request: LoadBoardListRequest): NetworkResponse<List<LoadBoardDataList>>{
        return safeApiCall { apiServiceEld.getLoadBoardList(header, request).body()!! }
    }

    override suspend fun getTripLoadList(header: String, request: LoadsRequest): NetworkResponse<List<LoadsList>>{
        return safeApiCall { apiServiceEld.getTripLoadList(header, request).body()!! }
    }

    override suspend fun addEditDescription(header: String, request: DescriptionRequest): NetworkResponse<DescriptionResponse>{
        return safeApiCall { apiServiceEld.addEditDescription(header, request).body()!! }
    }

    override suspend fun getDescription(header: String, request: DescriptionListRequest): NetworkResponse<List<DescriptionListResponse>>{
        return safeApiCall { apiServiceEld.getDescription(header, request).body()!! }
    }

    override suspend fun submitOffer(header: String, request: AddEditOfferRequest): NetworkResponse<JsonObject>{
        return safeApiCall { apiServiceEld.submitOffer(header, request).body()!! }
    }

    override suspend fun getOfferDetails(header: String, request: OfferDetailsRequest): NetworkResponse<List<OfferDetailsResponse>>{
        return safeApiCall { apiServiceEld.getOfferDetails(header, request).body()!! }
    }

}