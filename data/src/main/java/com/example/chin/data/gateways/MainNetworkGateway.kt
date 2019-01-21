package com.example.chin.data.gateways

import com.example.chin.data.entities.RechargablePointModel

interface MainNetworkGateway {

    suspend fun getRechargePoints(
        latitude: Double,
        longitude: Double,
        ratio: Long,
        apiToken: String
    ): List<RechargablePointModel>

}