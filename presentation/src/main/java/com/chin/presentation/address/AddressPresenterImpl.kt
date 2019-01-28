package com.chin.presentation.address

import com.chin.domain.usecase.GetAddressListUseCase
import com.chin.domain.usecase.SaveSelectedAddressUseCase
import javax.inject.Inject

class AddressPresenterImpl @Inject constructor(
    private val getAddressListUseCase: GetAddressListUseCase,
    private val saveSelectedAddressUseCase: SaveSelectedAddressUseCase
): AddressPresenter {

    private lateinit var view: AddressView

    override fun initialize(view: AddressView) {
        this.view = view

        getAddressListUseCase.executeAsync(Unit){
            view.setupAddresses(it.output ?: emptyList())
        }
    }

    override fun onAddressSelected(address: String) {
        if(address.isNotEmpty()){
            saveSelectedAddressUseCase.executeAsync(address){}
        }
    }


}