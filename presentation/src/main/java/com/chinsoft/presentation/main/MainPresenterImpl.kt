package com.chinsoft.presentation.main

import com.chinsoft.domain.entities.MapDataEntity
import com.chinsoft.domain.entities.ObtainSheltersInputEntity
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.domain.usecase.GetCachedSheltersUseCase
import com.chinsoft.domain.usecase.ObtainSheltersUseCase
import com.chinsoft.presentation.main.navigator.Navigator
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val obtainSheltersUseCase: ObtainSheltersUseCase,
    private val getCachedSheltersUseCase: GetCachedSheltersUseCase,
    private val navigator: Navigator
) : MainPresenter {

    lateinit var view: MainView
    var lastAddress: String? = null
    var shelterList: List<ShelterEntity> = emptyList()

    override fun initialize(view: MainView) {
        this.view = view
        getCachedShelters()
    }

    override fun obtainNearShelters(latitude: Double, longitude: Double) {

        view.showLoadingFooter(true)

        obtainSheltersUseCase.executeAsync(
            ObtainSheltersInputEntity(
                false,
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

    private fun getCachedShelters(){
        getCachedSheltersUseCase.executeAsync(Unit){
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

        if(point.url != null){
            navigator.openUrl(point.url!!)
        }

    }

    override fun onShelterSelected(point: ShelterEntity) {

        navigator.goToMapScreen(MapDataEntity(listOf(point)))

    }

    override fun onFabMapClicked() {

        navigator.goToMapScreen(MapDataEntity(shelterList))

    }

    override fun onSearchAllClicked() {

        view.showLoadingFooter(true)

        obtainSheltersUseCase.executeAsync(
            ObtainSheltersInputEntity(true,0.0, 0.0, null)
        ){

            view.showLoadingFooter(false)

            shelterList = it.output ?: emptyList()
            view.drawList(shelterList)

            showAddressOrHideIfNull()

            view.showSearchOkMessage()
        }

    }

    override fun onSearchByAddressClicked() {

        navigator.displaySearchByAddressDialog(lastAddress ?: ""){

            lastAddress = it

            view.showLoadingFooter(true)

            obtainSheltersUseCase.executeAsync(
                ObtainSheltersInputEntity(false,0.0, 0.0, lastAddress)
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