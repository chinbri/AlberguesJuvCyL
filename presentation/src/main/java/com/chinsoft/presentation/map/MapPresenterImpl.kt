package com.chinsoft.presentation.map

import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.presentation.main.navigator.Navigator
import javax.inject.Inject

class MapPresenterImpl @Inject constructor(
    val navigator: Navigator
): MapPresenter {

    lateinit var view: MapScreenView
    lateinit var rechargePointsList: List<ShelterEntity>

    override fun initialize(mapScreenView: MapScreenView, rechargePointsList: List<ShelterEntity>) {
        this.view = mapScreenView
        this.rechargePointsList = rechargePointsList
    }

    override fun onMapReady() {

        if(rechargePointsList.isNotEmpty()){

            view.setupMarkers(rechargePointsList)

        }

    }

    override fun onMarkerClicked(tag: String) {
        view.showRechargePointData(
            rechargePointsList.filter {
                tag == it.id
            }[0]
        )
    }

    override fun onInfoClicked(pointEntity: ShelterEntity) {
        navigator.openUrl(pointEntity.url)
    }

    override fun onNavigationClicked(pointEntity: ShelterEntity) {
        navigator.goToNavigation(pointEntity)
    }
}