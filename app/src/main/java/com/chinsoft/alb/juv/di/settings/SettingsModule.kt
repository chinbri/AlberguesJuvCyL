package com.chinsoft.alb.juv.di.settings

import com.chinsoft.presentation.settings.SettingsPresenter
import com.chinsoft.presentation.settings.SettingsPresenterImpl
import com.chinsoft.alb.juv.di.map.MapScope
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @Provides
    @MapScope
    fun providesSettingsPresenter(impl: SettingsPresenterImpl): SettingsPresenter = impl

}