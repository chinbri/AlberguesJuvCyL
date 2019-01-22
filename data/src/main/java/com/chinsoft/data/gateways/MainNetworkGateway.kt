package com.chinsoft.data.gateways

import com.chinsoft.data.entities.ShelterModel

interface MainNetworkGateway {

    suspend fun getAllRechargePoints(
        apiToken: String
    ): List<ShelterModel>

    suspend fun getRechargePointsFromPosition(
        latitude: Double,
        longitude: Double,
        ratio: Long,
        apiToken: String
    ): List<ShelterModel>

}