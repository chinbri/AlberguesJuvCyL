package com.chin.data.network

import com.chin.data.entities.RechargablePointsRequestModel
import com.chin.data.entities.ShelterModel

interface ApiService {

    suspend fun obtainAllPoints(apiToken: String) : List<ShelterModel>?

    suspend fun obtainPoints(request: RechargablePointsRequestModel) : List<ShelterModel>?

}