package com.chin.data.network

import com.chin.data.entities.RechargablePointsRequestModel
import com.chin.data.entities.ShelterModel
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(val api: SheltersApi): ApiService {


    override suspend fun obtainAllPoints(apiToken: String): List<ShelterModel>? {

        val responseCall = api.getAllShelters(apiToken)
        return responseCall.await()

    }


    override suspend fun obtainPoints(request: RechargablePointsRequestModel): List<ShelterModel>? {

        val responseCall = api.getSheltersFromPoint(request.buildMainRequest(), request.apiToken)
        return responseCall.await()

    }

}