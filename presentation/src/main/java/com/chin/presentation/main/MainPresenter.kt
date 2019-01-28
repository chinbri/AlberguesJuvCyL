package com.chin.presentation.main

import com.chin.domain.entities.ShelterEntity

enum class ListActions {
    INFO, NAVIGATION, SELECTION
}

interface MainPresenter {

    fun obtainNearShelters(latitude: Double, longitude: Double)

    fun initialize(view: MainView, firstExecution: Boolean)

    fun onNavigationSelected(it: ShelterEntity)

    fun onInfoClicked(point: ShelterEntity)

    fun onSearchByAddressClicked()

    fun onFabMapClicked()

    fun onMenuSettingsSelected()

    fun onMenuHelpSelected()

    fun onSearchAllClicked()

    fun onShelterSelected(point: ShelterEntity)

}