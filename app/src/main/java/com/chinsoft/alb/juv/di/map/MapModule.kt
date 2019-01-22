package com.chinsoft.alb.juv.di.map

import com.chinsoft.presentation.map.MapPresenter
import com.chinsoft.presentation.map.MapPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MapModule {

    @Provides
    @MapScope
    fun providesMapPresenter(impl: MapPresenterImpl): MapPresenter = impl

}