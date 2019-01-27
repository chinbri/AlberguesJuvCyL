package com.chin.data.gateways

import com.chin.data.entities.ShelterModel

interface MainLocalGateway {

    suspend fun getShelterList(): List<ShelterModel>

    suspend fun insertAll(points: List<ShelterModel>)

    suspend fun deleteItem(item: ShelterModel)

    suspend fun deleteAll()
}