package com.chinsoft.alb.juv.di.network

import com.chinsoft.alb.juv.BuildConfig
import com.chinsoft.data.network.ApiService
import com.chinsoft.data.network.ApiServiceImpl
import com.chinsoft.data.network.SheltersApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        val CONNECT_TIMEOUT = 120
    }

    @Provides
    @Singleton
    fun providesOkHttpClient():OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
            )
            .build()

    @Provides
    @Singleton
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl("https://analisis.datosabiertos.jcyl.es/resource/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

    @Provides
    @Singleton
    fun providesApiService(impl: ApiServiceImpl): ApiService = impl

    @Provides
    @Singleton
    fun providesRechargePointsApi(retrofit: Retrofit.Builder) = retrofit.build().create(SheltersApi::class.java)


}