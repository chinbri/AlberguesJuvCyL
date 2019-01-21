package com.example.chin.rechargepoints.di.main

import com.example.chin.rechargepoints.di.address.AddressModule
import com.example.chin.rechargepoints.ui.address.AddressDialogFragment
import dagger.Subcomponent

@Subcomponent(modules = [ AddressModule::class ])
@AddressScope
interface AddressSubComponent {

    fun inject(addressDialogFragment: AddressDialogFragment)

    @Subcomponent.Builder
    interface Builder {

        fun  addressModule(homeModule: AddressModule): Builder

        fun build(): AddressSubComponent

    }

}