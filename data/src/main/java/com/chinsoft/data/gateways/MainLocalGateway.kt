package com.chinsoft.data.gateways

import com.chinsoft.data.entities.ShelterModel

interface MainLocalGateway {

    suspend fun getShelterList(): List<ShelterModel>

    suspend fun insertAll(points: List<ShelterModel>)

    suspend fun deleteItem(item: ShelterModel)

    suspend fun deleteAll()
}