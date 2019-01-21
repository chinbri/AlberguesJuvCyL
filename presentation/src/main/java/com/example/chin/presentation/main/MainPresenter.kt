package com.example.chin.presentation.main

import com.example.chin.domain.entities.RechargePointEntity

enum class ListActions {
    INFO, NAVIGATION
}

interface MainPresenter {

    fun obtainNearRechargePoints(latitude: Double, longitude: Double)

    fun initialize(view: MainView)

    fun onNavigationSelected(it: RechargePointEntity)

    fun onInfoClicked(point: RechargePointEntity)

    fun onSearchByAddressClicked()

    fun onFabMapClicked()

    fun onMenuSettingsSelected()

    fun onMenuHelpSelected()

}