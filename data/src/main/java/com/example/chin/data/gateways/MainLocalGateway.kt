package com.example.chin.data.gateways

import com.example.chin.data.entities.ShelterModel

interface MainLocalGateway {

    suspend fun getRechargePoints(): List<ShelterModel>

    suspend fun insertAll(points: List<ShelterModel>)

    suspend fun deleteItem(item: ShelterModel)

    suspend fun deleteAll()
}