package com.chinsoft.data.gateways

import com.chinsoft.data.entities.ShelterModel

interface MainNetworkGateway {

    suspend fun getRechargePoints(
        latitude: Double,
        longitude: Double,
        ratio: Long,
        apiToken: String
    ): List<ShelterModel>

}