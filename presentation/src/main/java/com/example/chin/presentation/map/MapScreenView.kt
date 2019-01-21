package com.example.chin.presentation.map

import com.example.chin.domain.entities.ShelterEntity

interface MapScreenView {

    fun setupMarkers(rechargePointsList: List<ShelterEntity>)

    fun showRechargePointData(shelterEntity: ShelterEntity)

}