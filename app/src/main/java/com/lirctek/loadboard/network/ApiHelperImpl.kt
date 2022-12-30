package com.lirctek.loadboard.network

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


}