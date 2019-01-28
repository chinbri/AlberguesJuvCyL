package com.chin.alb.di.main

import com.chin.alb.juv.di.address.AddressModule
import com.chin.alb.juv.ui.address.AddressDialogFragment
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