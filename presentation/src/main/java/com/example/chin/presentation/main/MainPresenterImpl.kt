package com.example.chin.presentation.main

import com.example.chin.domain.entities.MapDataEntity
import com.example.chin.domain.entities.ObtainRechargePointsInputEntity
import com.example.chin.domain.entities.RechargePointEntity
import com.example.chin.domain.usecase.GetCachedRechargePointsUseCase
import com.example.chin.domain.usecase.ObtainRechargePointsUseCase
import com.example.chin.presentation.main.navigator.Navigator
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val obtainRechargePointsUseCase: ObtainRechargePointsUseCase,
    private val getCachedRechargePointsUseCase: GetCachedRechargePointsUseCase,
    private val navigator: Navigator
) : MainPresenter {

    lateinit var view: MainView
    var lastAddress: String? = null
    var rechargePointList: List<RechargePointEntity> = emptyList()

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


            rechargePointList = it.output ?: emptyList()
            lastAddress = null

            showAddressOrHideIfNull()
            view.drawList(rechargePointList)

            view.showSearchOkMessage()
        }

    }

    private fun getCachedRechargePoints(){
        getCachedRechargePointsUseCase.executeAsync(Unit){
            rechargePointList = it.output?.pointList ?: emptyList()
            lastAddress = it.output?.lastAddress
            showAddressOrHideIfNull()
            view.drawList(rechargePointList)
        }
    }

    override fun onNavigationSelected(it: RechargePointEntity) {

        navigator.goToNavigation(it)

    }

    override fun onInfoClicked(point: RechargePointEntity) {

        navigator.openUrl(point.url)

    }

    override fun onFabMapClicked() {

        navigator.goToMapScreen(MapDataEntity(rechargePointList))

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

                    rechargePointList = it.output ?: emptyList()
                    view.drawList(rechargePointList)

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