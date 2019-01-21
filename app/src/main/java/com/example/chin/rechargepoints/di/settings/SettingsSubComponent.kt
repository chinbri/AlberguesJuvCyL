package com.example.chin.rechargepoints.di.settings

import com.example.chin.rechargepoints.di.map.MapScope
import com.example.chin.rechargepoints.ui.settings.SettingsDialogFragment
import dagger.Subcomponent

@Subcomponent(modules = [ SettingsModule::class ])
@MapScope
interface SettingsSubComponent {

    fun inject(fragment: SettingsDialogFragment)

    @Subcomponent.Builder
    interface Builder {

        fun  settingsModule(settingsModule: SettingsModule): Builder

        fun build(): SettingsSubComponent

    }

}