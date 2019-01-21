package com.example.chin.data.gateways

import com.example.chin.data.entities.RechargablePointsRequestModel
import com.example.chin.data.network.ApiService
import javax.inject.Inject

class MainNetworkGatewayImpl @Inject constructor(val apiService: ApiService): MainNetworkGateway {

    override suspend fun getRechargePoints(
        latitude: Double,
        longitude: Double,
        ratio: Long,
        apiToken: String
    ) =
        apiService.obtainPoints(
            RechargablePointsRequestModel(latitude, longitude, ratio, apiToken)
        ) ?: emptyList()

}