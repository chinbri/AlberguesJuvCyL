package com.chinsoft.domain.entities

data class CachedRechargePointsDataEntity(

    val pointList: List<ShelterEntity>,
    val lastAddress: String?

)