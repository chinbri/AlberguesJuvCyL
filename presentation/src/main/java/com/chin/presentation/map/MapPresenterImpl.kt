package com.chin.presentation.map

import com.chin.domain.entities.ShelterEntity
import com.chin.presentation.main.navigator.Navigator
import javax.inject.Inject

class MapPresenterImpl @Inject constructor(
    val navigator: Navigator
): MapPresenter {

    lateinit var view: MapScreenView
    lateinit var shelterList: List<ShelterEntity>

    private var footerMinimized = false

    override fun initialize(mapScreenView: MapScreenView, shelterList: List<ShelterEntity>) {
        this.view = mapScreenView
        this.shelterList = shelterList
    }

    override fun onMapReady() {

        if(shelterList.isNotEmpty()){

            view.setupMarkers(
                shelterList,
                if(shelterList.size == 1) { shelterList[0] } else { null }
            )

            if(shelterList.size == 1){
                onMarkerClicked(shelterList[0].id)
            }

        }

    }

    override fun onMarkerClicked(tag: String) {
        view.showShelterData(
            shelterList.filter {
                tag == it.id
            }[0]
        )
        if(footerMinimized){
            onRedimFooterClicked()
        }
    }

    override fun onInfoClicked(pointEntity: ShelterEntity) {
        if(pointEntity.url != null){
            navigator.openUrl(pointEntity.url!!)
        }
    }

    override fun onNavigationClicked(pointEntity: ShelterEntity) {
        navigator.goToNavigation(pointEntity)
    }

    override fun onRedimFooterClicked() {
        if(footerMinimized){
            view.maximizeFooter()
        }else{
            view.minimizeFooter()
        }
        footerMinimized = !footerMinimized
    }
}