package com.example.chin.data.network

import com.example.chin.data.entities.RechargablePointsRequestModel
import com.example.chin.data.entities.ShelterModel

interface ApiService {

    suspend fun obtainPoints(request: RechargablePointsRequestModel) : List<ShelterModel>?

}