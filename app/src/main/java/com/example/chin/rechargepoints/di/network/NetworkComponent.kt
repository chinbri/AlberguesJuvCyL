package com.example.chin.rechargepoints.di.network

import com.example.chin.data.network.ApiService
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [NetworkModule::class]
)
@Singleton
interface NetworkComponent {

    fun providesApiService(): ApiService


}