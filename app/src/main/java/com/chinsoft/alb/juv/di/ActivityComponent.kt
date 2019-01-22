package com.chinsoft.alb.juv.di

import com.chinsoft.presentation.main.navigator.Navigator
import com.chinsoft.alb.di.main.AddressSubComponent
import com.chinsoft.alb.juv.di.main.MainSubComponent
import com.chinsoft.alb.juv.di.map.MapSubComponent
import com.chinsoft.alb.juv.di.settings.SettingsSubComponent
import dagger.Component
import kotlinx.coroutines.Job


@Component(
    modules = [ActivityModule::class],
    dependencies = [ApplicationComponent::class]
)
@ActivityScope
interface ActivityComponent {

    fun mainComponentBuilder(): MainSubComponent.Builder

    fun addressComponentBuilder(): AddressSubComponent.Builder

    fun mapComponentBuilder(): MapSubComponent.Builder

    fun settingsComponentBuilder(): SettingsSubComponent.Builder

    fun providesJob(): Job

    fun providesNavigator(): Navigator

}