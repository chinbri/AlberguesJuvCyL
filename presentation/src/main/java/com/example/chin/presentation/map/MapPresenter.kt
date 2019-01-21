package com.example.chin.presentation.map

import com.example.chin.domain.entities.ShelterEntity

interface MapPresenter {

    fun initialize(mapScreenView: MapScreenView, rechargePointsList: List<ShelterEntity>)

    fun onMapReady()

    fun onMarkerClicked(tag: String)

    fun onInfoClicked(pointEntity: ShelterEntity)

    fun onNavigationClicked(pointEntity: ShelterEntity)

}