package com.chin.data.gateways

import com.chin.data.entities.ShelterModel

interface MainNetworkGateway {

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