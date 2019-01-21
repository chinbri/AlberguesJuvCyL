package com.example.chin.presentation.map

import com.example.chin.domain.entities.RechargePointEntity

interface MapScreenView {

    fun setupMarkers(rechargePointsList: List<RechargePointEntity>)

    fun showRechargePointData(rechargePointEntity: RechargePointEntity)

}