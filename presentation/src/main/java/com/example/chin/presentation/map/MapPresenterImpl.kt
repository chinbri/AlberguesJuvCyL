package com.example.chin.presentation.map

import com.example.chin.domain.entities.RechargePointEntity
import com.example.chin.presentation.main.navigator.Navigator
import javax.inject.Inject

class MapPresenterImpl @Inject constructor(
    val navigator: Navigator
): MapPresenter {

    lateinit var view: MapScreenView
    lateinit var rechargePointsList: List<RechargePointEntity>

    override fun initialize(mapScreenView: MapScreenView, rechargePointsList: List<RechargePointEntity>) {
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

    override fun onInfoClicked(pointEntity: RechargePointEntity) {
        navigator.openUrl(pointEntity.url)
    }

    override fun onNavigationClicked(pointEntity: RechargePointEntity) {
        navigator.goToNavigation(pointEntity)
    }
}