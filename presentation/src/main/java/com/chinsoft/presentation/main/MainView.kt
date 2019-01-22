package com.chinsoft.presentation.main

import com.chinsoft.domain.entities.ShelterEntity

interface MainView {

    fun showMessage(message: String)

    fun drawList(items: List<ShelterEntity>)
    fun showSearchOkMessage()
    fun showAddresNotFoundMessage()

    fun showCurrentAddressSearch(address: String)
    fun hideCurrentAddressSearch()
    fun showLoadingFooter(loading: Boolean)
}