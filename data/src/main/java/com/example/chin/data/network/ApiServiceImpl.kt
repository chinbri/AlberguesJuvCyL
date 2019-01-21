package com.example.chin.data.network

import com.example.chin.data.entities.RechargablePointsRequestModel
import com.example.chin.data.entities.ShelterModel
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(val api: RechargePointsApi): ApiService {


    override suspend fun obtainPoints(request: RechargablePointsRequestModel): List<ShelterModel>? {

        val responseCall = api.getRechagePoints(request.buildMainRequest(), request.apiToken)
        return responseCall.await()

        //TODO exception control here

    }

}