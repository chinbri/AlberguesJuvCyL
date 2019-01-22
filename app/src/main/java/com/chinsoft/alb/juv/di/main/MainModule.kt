package com.chinsoft.alb.juv.di.main

import com.chinsoft.data.gateways.MainLocalGateway
import com.chinsoft.data.gateways.MainLocalGatewayImpl
import com.chinsoft.data.gateways.MainNetworkGateway
import com.chinsoft.data.gateways.MainNetworkGatewayImpl
import com.chinsoft.domain.usecase.GetCachedSheltersUseCase
import com.chinsoft.domain.usecase.ObtainSheltersUseCase
import com.chinsoft.domain.usecase.main.GetCachedSheltersUseCaseImpl
import com.chinsoft.domain.usecase.main.ObtainSheltersUseCaseImpl
import com.chinsoft.domain.util.LocationUtils
import com.chinsoft.presentation.main.MainPresenter
import com.chinsoft.presentation.main.MainPresenterImpl
import com.chinsoft.alb.juv.utils.LocationUtilsImpl
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