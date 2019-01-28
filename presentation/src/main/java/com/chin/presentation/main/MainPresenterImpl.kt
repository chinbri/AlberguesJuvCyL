package com.chin.presentation.main

import com.chin.domain.entities.MapDataEntity
import com.chin.domain.entities.ObtainSheltersInputEntity
import com.chin.domain.entities.ShelterEntity
import com.chin.domain.usecase.*
import com.chin.presentation.main.navigator.Navigator
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val obtainSheltersUseCase: ObtainSheltersUseCase,
    private val getCachedSheltersUseCase: GetCachedSheltersUseCase,
    private val navigator: Navigator
) : MainPresenter {

    lateinit var view: MainView
    var lastAddress: String? = null
    var shelterList: List<ShelterEntity> = emptyList()

    override fun initialize(view: MainView, firstExecution: Boolean) {
        this.view = view
        getCachedShelters()

        if(firstExecution){
            navigator.goToWelcomeWizard()
        }
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
            processSearchResponse(it)
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

            processSearchResponse(it)

        }

    }

    private fun processSearchResponse(it: UseCaseResponse<List<ShelterEntity>>) {

        view.showLoadingFooter(false)
        if(it.existNotification()){

            when (it.notification){
                is NetworkErrorNotification ->  view.showNetworkErrorMessage()
                is AddressNotFoundNotification ->  view.showAddresNotFoundMessage()
            }

        }else{

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
            ){ useCaseResponse ->

                processSearchResponse(useCaseResponse)

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