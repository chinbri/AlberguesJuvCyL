package com.chinsoft.alb.juv.di.network

import com.chinsoft.data.network.ApiService
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [NetworkModule::class]
)
@Singleton
interface NetworkComponent {

    fun providesApiService(): ApiService


}