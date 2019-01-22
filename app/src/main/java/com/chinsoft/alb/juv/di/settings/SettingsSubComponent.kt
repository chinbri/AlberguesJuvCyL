package com.chinsoft.alb.juv.di.settings

import com.chinsoft.alb.juv.di.map.MapScope
import com.chinsoft.alb.juv.ui.settings.SettingsDialogFragment
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