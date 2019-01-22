package com.chinsoft.data.entities

class RechargablePointsRequestModel(
    val latitude: Double,
    val longitude: Double,
    val ratio: Long,
    val apiToken: String
) {
    fun buildMainRequest() = "within_circle(posicion,$latitude,$longitude,$ratio)"
}