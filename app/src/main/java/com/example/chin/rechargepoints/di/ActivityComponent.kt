package com.example.chin.rechargepoints.di

import com.example.chin.presentation.main.navigator.Navigator
import com.example.chin.rechargepoints.di.main.AddressSubComponent
import com.example.chin.rechargepoints.di.main.MainSubComponent
import com.example.chin.rechargepoints.di.map.MapSubComponent
import com.example.chin.rechargepoints.di.settings.SettingsSubComponent
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