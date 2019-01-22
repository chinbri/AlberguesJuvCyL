package com.chinsoft.domain.entities

data class ObtainRechargePointsInputEntity(
    var latitude: Double,
    var longitude: Double,
    val address: String?
)