package com.chin.data.gateways

import com.chin.data.entities.ShelterModel
import java.io.IOException

interface MainNetworkGateway {

    @Throws(IOException::class)
    suspend fun getAllShelters(
        apiToken: String
    ): List<ShelterModel>

    suspend fun getSheltersFromPosition(
        latitude: Double,
        longitude: Double,
        ratio: Long,
        apiToken: String
    ): List<ShelterModel>

}