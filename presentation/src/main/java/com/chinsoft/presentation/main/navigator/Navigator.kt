package com.chinsoft.presentation.main.navigator

import com.chinsoft.domain.entities.MapDataEntity
import com.chinsoft.domain.entities.ShelterEntity

interface Navigator {

    fun goToNavigation(pointEntity: ShelterEntity)

    fun openUrl(url: String)

    fun displaySearchByAddressDialog(lastAddress: String, listener: (newAddress: String) -> Unit)

    fun goToMapScreen(mapDataEntity: MapDataEntity)

    fun displaySettingsDialog()

    fun displayAboutDialog()

}