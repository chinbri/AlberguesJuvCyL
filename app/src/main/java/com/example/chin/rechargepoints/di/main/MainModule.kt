package com.example.chin.rechargepoints.di.main

import com.example.chin.data.gateways.MainLocalGateway
import com.example.chin.data.gateways.MainLocalGatewayImpl
import com.example.chin.data.gateways.MainNetworkGateway
import com.example.chin.data.gateways.MainNetworkGatewayImpl
import com.example.chin.domain.usecase.GetCachedRechargePointsUseCase
import com.example.chin.domain.usecase.ObtainRechargePointsUseCase
import com.example.chin.domain.usecase.main.GetCachedRechargePointsUseCaseImpl
import com.example.chin.domain.usecase.main.ObtainRechargePointsUseCaseImpl
import com.example.chin.domain.util.LocationUtils
import com.example.chin.presentation.main.MainPresenter
import com.example.chin.presentation.main.MainPresenterImpl
import com.example.chin.rechargepoints.utils.LocationUtilsImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @MainScope
    fun providesMainPresenter(impl: MainPresenterImpl):MainPresenter = impl

    @Provides
    @MainScope
    fun providesMainUseCase(impl: ObtainRechargePointsUseCaseImpl): ObtainRechargePointsUseCase = impl

    @Provides
    @MainScope
    fun providesMainLocalGateway(impl: MainLocalGatewayImpl): MainLocalGateway = impl

    @Provides
    @MainScope
    fun providesMainNerworkGateway(impl: MainNetworkGatewayImpl): MainNetworkGateway = impl

    @Provides
    @MainScope
    fun providesGetCachedRechargePointsUseCase(impl: GetCachedRechargePointsUseCaseImpl): GetCachedRechargePointsUseCase = impl

    @Provides
    @MainScope
    fun providesLocationUtils(impl: LocationUtilsImpl): LocationUtils = impl

}