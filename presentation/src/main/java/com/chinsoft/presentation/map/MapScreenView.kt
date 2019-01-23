package com.chinsoft.presentation.map

import com.chinsoft.domain.entities.ShelterEntity

interface MapScreenView {

    fun setupMarkers(shelterList: List<ShelterEntity>)

    fun showShelterData(shelterEntity: ShelterEntity)

    fun minimizeFooter()
    fun maximizeFooter()
}