package com.chin.alb.juv.di.main

import com.chin.data.gateways.MainLocalGateway
import com.chin.data.gateways.MainLocalGatewayImpl
import com.chin.data.gateways.MainNetworkGateway
import com.chin.data.gateways.MainNetworkGatewayImpl
import com.chin.domain.usecase.GetCachedSheltersUseCase
import com.chin.domain.usecase.ObtainSheltersUseCase
import com.chin.domain.usecase.main.GetCachedSheltersUseCaseImpl
import com.chin.domain.usecase.main.ObtainSheltersUseCaseImpl
import com.chin.domain.util.LocationUtils
import com.chin.presentation.main.MainPresenter
import com.chin.presentation.main.MainPresenterImpl
import com.chin.alb.juv.utils.LocationUtilsImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @MainScope
    fun providesMainPresenter(impl: MainPresenterImpl):MainPresenter = impl

    @Provides
    @MainScope
    fun providesMainUseCase(impl: ObtainSheltersUseCaseImpl): ObtainSheltersUseCase = impl

    @Provides
    @MainScope
    fun providesMainLocalGateway(impl: MainLocalGatewayImpl): MainLocalGateway = impl

    @Provides
    @MainScope
    fun providesMainNerworkGateway(impl: MainNetworkGatewayImpl): MainNetworkGateway = impl

    @Provides
    @MainScope
    fun providesGetCachedSheltersUseCase(impl: GetCachedSheltersUseCaseImpl): GetCachedSheltersUseCase = impl

    @Provides
    @MainScope
    fun providesLocationUtils(impl: LocationUtilsImpl): LocationUtils = impl

}