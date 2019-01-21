package com.example.chin.presentation.main

import com.example.chin.domain.entities.ShelterEntity

interface MainView {

    fun showMessage(message: String)

    fun drawList(items: List<ShelterEntity>)
    fun showSearchOkMessage()
    fun showAddresNotFoundMessage()

    fun showCurrentAddressSearch(address: String)
    fun hideCurrentAddressSearch()
    fun showLoadingFooter(loading: Boolean)
}