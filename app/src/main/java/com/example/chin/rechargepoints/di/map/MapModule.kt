package com.example.chin.rechargepoints.di.map

import com.example.chin.presentation.map.MapPresenter
import com.example.chin.presentation.map.MapPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MapModule {

    @Provides
    @MapScope
    fun providesMapPresenter(impl: MapPresenterImpl): MapPresenter = impl

}