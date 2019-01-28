package com.chin.alb.juv.di.settings

import com.chin.presentation.settings.SettingsPresenter
import com.chin.presentation.settings.SettingsPresenterImpl
import com.chin.alb.juv.di.map.MapScope
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @Provides
    @MapScope
    fun providesSettingsPresenter(impl: SettingsPresenterImpl): SettingsPresenter = impl

}