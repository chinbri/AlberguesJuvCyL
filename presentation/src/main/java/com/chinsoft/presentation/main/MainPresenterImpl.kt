package com.chinsoft.presentation.main

import com.chinsoft.domain.entities.MapDataEntity
import com.chinsoft.domain.entities.ObtainRechargePointsInputEntity
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.domain.usecase.GetCachedRechargePointsUseCase
import com.chinsoft.domain.usecase.ObtainRechargePointsUseCase
import com.chinsoft.presentation.main.navigator.Navigator
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val obtainRechargePointsUseCase: ObtainRechargePointsUseCase,
    private val getCachedRechargePointsUseCase: GetCachedRechargePointsUseCase,
    private val navigator: Navigator
) : MainPresenter {

    lateinit var view: MainView
    var lastAddress: String? = null
    var shelterList: List<ShelterEntity> = emptyList()

    override fun initialize(view: MainView) {
        this.view = view
        getCachedRechargePoints()
    }

    override fun obtainNearRechargePoints(latitude: Double, longitude: Double) {

        view.showLoadingFooter(true)

        obtainRechargePointsUseCase.executeAsync(
            ObtainRechargePointsInputEntity(
                latitude,
                longitude,
                null
            )
        ){

            view.showLoadingFooter(false)


            shelterList = it.output ?: emptyList()
            lastAddress = null

            showAddressOrHideIfNull()
            view.drawList(shelterList)

            view.showSearchOkMessage()
        }

    }

    private fun getCachedRechargePoints(){
        getCachedRechargePointsUseCase.executeAsync(Unit){
            shelterList = it.output?.pointList ?: emptyList()
            lastAddress = it.output?.lastAddress
            showAddressOrHideIfNull()
            view.drawList(shelterList)
        }
    }

    override fun onNavigationSelected(it: ShelterEntity) {

        navigator.goToNavigation(it)

    }

    override fun onInfoClicked(point: ShelterEntity) {

        navigator.openUrl(point.url)

    }

    override fun onFabMapClicked() {

        navigator.goToMapScreen(MapDataEntity(shelterList))

    }

    override fun onSearchByAddressClicked() {

        navigator.displaySearchByAddressDialog(lastAddress ?: ""){

            lastAddress = it

            view.showLoadingFooter(true)

            obtainRechargePointsUseCase.executeAsync(
                ObtainRechargePointsInputEntity(0.0, 0.0, lastAddress)
            ){

                view.showLoadingFooter(false)
                if(it.existNotification()){

                    view.showAddresNotFoundMessage()

                }else{

                    shelterList = it.output ?: emptyList()
                    view.drawList(shelterList)

                    showAddressOrHideIfNull()
                    view.showSearchOkMessage()

                }

            }
        }

    }

    private fun showAddressOrHideIfNull() {
        if(lastAddress != null){
            view.showCurrentAddressSearch(lastAddress!!)
        }else{
            view.hideCurrentAddressSearch()
        }

    }

    override fun onMenuSettingsSelected() {
        navigator.displaySettingsDialog()
    }

    override fun onMenuHelpSelected() {
        navigator.displayAboutDialog()
    }
}