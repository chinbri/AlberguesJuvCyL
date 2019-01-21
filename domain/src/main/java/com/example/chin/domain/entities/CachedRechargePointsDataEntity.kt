package com.example.chin.domain.entities

data class CachedRechargePointsDataEntity(

    val pointList: List<ShelterEntity>,
    val lastAddress: String?

)