package com.example.chin.presentation.main

import com.example.chin.domain.entities.RechargePointEntity

interface MainView {

    fun showMessage(message: String)

    fun drawList(items: List<RechargePointEntity>)
    fun showSearchOkMessage()
    fun showAddresNotFoundMessage()

    fun showCurrentAddressSearch(address: String)
    fun hideCurrentAddressSearch()
    fun showLoadingFooter(loading: Boolean)
}