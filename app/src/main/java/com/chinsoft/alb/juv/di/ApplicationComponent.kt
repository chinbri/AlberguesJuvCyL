package com.chinsoft.alb.juv.di

import com.chinsoft.domain.util.PreferenceUtils
import com.chinsoft.alb.juv.RechargePointsApplication
import com.chinsoft.alb.juv.di.network.NetworkComponent
import com.chinsoft.alb.juv.di.network.NetworkModule
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton


@Component(modules = [ ApplicationModule::class, NetworkModule::class ] )
@Singleton
interface ApplicationComponent: NetworkComponent {

    fun inject(application: RechargePointsApplication)

    @Named("datosAbiertosApiKey")
    fun provideDatosAbiertosApiKey(): String

    fun providePreferenceUtils(): PreferenceUtils

}