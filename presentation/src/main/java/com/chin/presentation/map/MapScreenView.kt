package com.chin.presentation.map

import com.chin.domain.entities.ShelterEntity

interface MapScreenView {

    fun setupMarkers(shelterList: List<ShelterEntity>, onlyShelter: ShelterEntity?)

    fun showShelterData(shelterEntity: ShelterEntity)

    fun minimizeFooter()
    fun maximizeFooter()
}