package com.chinsoft.data.network

import com.chinsoft.data.entities.ShelterModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RechargePointsApi {

    @GET("3f5r-8hut.json")
    fun getRechagePoints(
        @Query("\$where") query: String,
        @Query("\$\$app_token") appToken: String
    ): Deferred<List<ShelterModel>>

}