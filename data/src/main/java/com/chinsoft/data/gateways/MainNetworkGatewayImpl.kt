package com.chinsoft.data.gateways

import com.chinsoft.data.entities.RechargablePointsRequestModel
import com.chinsoft.data.entities.ShelterModel
import com.chinsoft.data.network.ApiService
import javax.inject.Inject

class MainNetworkGatewayImpl @Inject constructor(val apiService: ApiService): MainNetworkGateway {


    override suspend fun getAllShelters(apiToken: String): List<ShelterModel>  =
        apiService.obtainAllPoints(apiToken) ?: emptyList()


    override suspend fun getSheltersFromPosition(
        latitude: Double,
        longitude: Double,
        ratio: Long,
        apiToken: String
    ) =
        apiService.obtainPoints(
            RechargablePointsRequestModel(latitude, longitude, ratio, apiToken)
        ) ?: emptyList()

}