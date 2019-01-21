package com.example.chin.rechargepoints.di.settings

import com.example.chin.presentation.settings.SettingsPresenter
import com.example.chin.presentation.settings.SettingsPresenterImpl
import com.example.chin.rechargepoints.di.map.MapScope
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @Provides
    @MapScope
    fun providesSettingsPresenter(impl: SettingsPresenterImpl): SettingsPresenter = impl

}