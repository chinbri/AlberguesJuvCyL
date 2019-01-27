package com.chin.alb.juv.di.main

import com.chin.alb.juv.ui.main.MainActivity
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