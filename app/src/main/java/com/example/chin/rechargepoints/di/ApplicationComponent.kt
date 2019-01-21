package com.example.chin.rechargepoints.di

import com.example.chin.domain.util.PreferenceUtils
import com.example.chin.rechargepoints.RechargePointsApplication
import com.example.chin.rechargepoints.di.network.NetworkComponent
import com.example.chin.rechargepoints.di.network.NetworkModule
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton


@Component(modules = [ ApplicationModule::class, NetworkModule::class ] )
@Singleton
interface ApplicationComponent: NetworkComponent{

    fun inject(application: RechargePointsApplication)

    @Named("datosAbiertosApiKey")
    fun provideDatosAbiertosApiKey(): String

    fun providePreferenceUtils(): PreferenceUtils

}