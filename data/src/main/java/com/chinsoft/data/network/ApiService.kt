package com.chinsoft.data.network

import com.chinsoft.data.entities.RechargablePointsRequestModel
import com.chinsoft.data.entities.ShelterModel

interface ApiService {

    suspend fun obtainAllPoints(apiToken: String) : List<ShelterModel>?

    suspend fun obtainPoints(request: RechargablePointsRequestModel) : List<ShelterModel>?

}