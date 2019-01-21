package com.example.chin.rechargepoints.di.main

import com.example.chin.rechargepoints.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [ MainModule::class ])
@MainScope
interface MainSubComponent {

    fun inject(homeActivity: MainActivity)

    @Subcomponent.Builder
    interface Builder {

        fun  mainModule(homeModule: MainModule): Builder

        fun build(): MainSubComponent

    }

}