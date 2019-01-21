package com.example.chin.domain.entities

data class CachedRechargePointsDataEntity(

    val pointList: List<RechargePointEntity>,
    val lastAddress: String?

)