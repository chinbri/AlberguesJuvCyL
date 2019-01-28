package com.chin.alb.juv.di.map

import com.chin.alb.juv.ui.map.MapActivity
import dagger.Subcomponent

@Subcomponent(modules = [ MapModule::class ])
@MapScope
interface MapSubComponent {

    fun inject(mapActivity: MapActivity)

    @Subcomponent.Builder
    interface Builder {

        fun  mapModule(mapModule: MapModule): Builder

        fun build(): MapSubComponent

    }

}