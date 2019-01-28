package com.chin.domain.entities

data class CachedSheltersDataEntity(

    val pointList: List<ShelterEntity>,
    val lastAddress: String?

)