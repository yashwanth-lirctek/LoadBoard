package com.lirctek.loadboard.network

import com.lirctek.loadboard.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @BASEURL
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @BASEURLELD
    @Provides
    fun provideBaseUrlEld() = BuildConfig.ELD_BASE_URL

    @BASEURL
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @BASEURL
    @Singleton
    @Provides
    fun provideRetrofit(@BASEURL okHttpClient: OkHttpClient, @BASEURL BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @BASEURL
    @Provides
    @Singleton
    fun provideApiService(@BASEURL retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @BASEURLELD
    @Singleton
    @Provides
    fun provideOkHttpClientELD() = if (BuildConfig.DEBUG){
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient
            .Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @BASEURLELD
    @Singleton
    @Provides
    fun provideRetrofitELD(@BASEURLELD okHttpClientELD: OkHttpClient, @BASEURLELD BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClientELD)
        .build()

    @BASEURLELD
    @Provides
    @Singleton
    fun provideApiServiceEld(@BASEURLELD retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BASEURL

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BASEURLELD