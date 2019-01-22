package com.chinsoft.presentation.address

interface AddressPresenter {

    fun initialize(view: AddressView)

    fun onAddressSelected(address: String)

}