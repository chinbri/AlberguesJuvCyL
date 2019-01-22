package com.chinsoft.domain.entities

data class CachedSheltersDataEntity(

    val pointList: List<ShelterEntity>,
    val lastAddress: String?

)