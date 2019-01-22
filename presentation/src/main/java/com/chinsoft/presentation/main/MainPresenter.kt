package com.chinsoft.presentation.main

import com.chinsoft.domain.entities.ShelterEntity

enum class ListActions {
    INFO, NAVIGATION
}

interface MainPresenter {

    fun obtainNearRechargePoints(latitude: Double, longitude: Double)

    fun initialize(view: MainView)

    fun onNavigationSelected(it: ShelterEntity)

    fun onInfoClicked(point: ShelterEntity)

    fun onSearchByAddressClicked()

    fun onFabMapClicked()

    fun onMenuSettingsSelected()

    fun onMenuHelpSelected()

}