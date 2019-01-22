package com.chinsoft.presentation.map

import com.chinsoft.domain.entities.ShelterEntity

interface MapScreenView {

    fun setupMarkers(rechargePointsList: List<ShelterEntity>)

    fun showRechargePointData(shelterEntity: ShelterEntity)

}