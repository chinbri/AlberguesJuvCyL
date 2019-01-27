package com.chin.data.network

import com.chin.data.entities.ShelterModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SheltersApi {

    @GET("3f5r-8hut.json")
    fun getAllShelters(
        @Query("\$\$app_token") appToken: String
    ): Deferred<List<ShelterModel>>

    @GET("3f5r-8hut.json")
    fun getAllShelters(
        @Query("\$where") query: String,
        @Query("\$\$app_token") appToken: String
    ): Deferred<List<ShelterModel>>

}