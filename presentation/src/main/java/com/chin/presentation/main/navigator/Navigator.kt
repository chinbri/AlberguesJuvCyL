package com.chin.presentation.main.navigator

import com.chin.domain.entities.MapDataEntity
import com.chin.domain.entities.ShelterEntity

interface Navigator {

    fun goToNavigation(pointEntity: ShelterEntity)

    fun openUrl(url: String)

    fun displaySearchByAddressDialog(lastAddress: String, listener: (newAddress: String) -> Unit)

    fun goToMapScreen(mapDataEntity: MapDataEntity)

    fun displaySettingsDialog()

    fun displayAboutDialog()

}