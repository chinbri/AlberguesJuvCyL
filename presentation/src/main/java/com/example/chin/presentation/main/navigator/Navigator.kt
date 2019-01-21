package com.example.chin.presentation.main.navigator

import com.example.chin.domain.entities.MapDataEntity
import com.example.chin.domain.entities.ShelterEntity

interface Navigator {

    fun goToNavigation(pointEntity: ShelterEntity)

    fun openUrl(url: String)

    fun displaySearchByAddressDialog(lastAddress: String, listener: (newAddress: String) -> Unit)

    fun goToMapScreen(mapDataEntity: MapDataEntity)

    fun displaySettingsDialog()

    fun displayAboutDialog()

}