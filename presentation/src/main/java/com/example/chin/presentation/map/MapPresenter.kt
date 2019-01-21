package com.example.chin.presentation.map

import com.example.chin.domain.entities.RechargePointEntity

interface MapPresenter {

    fun initialize(mapScreenView: MapScreenView, rechargePointsList: List<RechargePointEntity>)

    fun onMapReady()

    fun onMarkerClicked(tag: String)

    fun onInfoClicked(pointEntity: RechargePointEntity)

    fun onNavigationClicked(pointEntity: RechargePointEntity)

}