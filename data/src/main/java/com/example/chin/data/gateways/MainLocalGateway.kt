package com.example.chin.data.gateways

import com.example.chin.data.entities.RechargablePointModel

interface MainLocalGateway {

    suspend fun getRechargePoints(): List<RechargablePointModel>

    suspend fun insertAll(points: List<RechargablePointModel>)

    suspend fun deleteItem(item: RechargablePointModel)

    suspend fun deleteAll()
}