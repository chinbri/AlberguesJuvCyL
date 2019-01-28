package com.chin.data.network

import com.chin.data.entities.RechargablePointsRequestModel
import com.chin.data.entities.ShelterModel
import java.io.IOException

interface ApiService {

    @Throws(IOException::class)
    suspend fun obtainAllPoints(apiToken: String) : List<ShelterModel>?

    suspend fun obtainPoints(request: RechargablePointsRequestModel) : List<ShelterModel>?

}