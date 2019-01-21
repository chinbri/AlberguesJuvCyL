package com.example.chin.presentation.address

interface AddressPresenter {

    fun initialize(view: AddressView)

    fun onAddressSelected(address: String)

}