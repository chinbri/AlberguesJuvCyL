package com.chin.alb.juv.di

import com.chin.domain.util.PreferenceUtils
import com.chin.alb.juv.ShelterApplication
import com.chin.alb.juv.di.network.NetworkComponent
import com.chin.alb.juv.di.network.NetworkModule
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton


@Component(modules = [ ApplicationModule::class, NetworkModule::class ] )
@Singleton
interface ApplicationComponent: NetworkComponent {

    fun inject(application: ShelterApplication)

    @Named("datosAbiertosApiKey")
    fun provideDatosAbiertosApiKey(): String

    fun providePreferenceUtils(): PreferenceUtils

}