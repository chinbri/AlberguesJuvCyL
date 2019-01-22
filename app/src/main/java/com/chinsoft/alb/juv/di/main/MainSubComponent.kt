package com.chinsoft.alb.juv.di.main

import com.chinsoft.alb.juv.ui.main.MainActivity
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