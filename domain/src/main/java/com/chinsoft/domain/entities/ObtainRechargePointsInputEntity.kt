package com.chinsoft.domain.entities

data class ObtainRechargePointsInputEntity(
    var searchAll: Boolean,
    var latitude: Double,
    var longitude: Double,
    val address: String?
)