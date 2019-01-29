package com.chin.presentation.main

import com.chin.domain.entities.ShelterEntity

interface MainView {

    fun showMessage(message: String)

    fun drawList(items: List<ShelterEntity>)
    fun showSearchOkMessage()
    fun showAddresNotFoundMessage()

    fun clearSubtitle()
    fun showLoadingFooter(loading: Boolean)
    fun showNetworkErrorMessage()
    fun showAddressSubtitle(address: String)
    fun showAllSubtitle()
    fun showNearSubtitle()
}