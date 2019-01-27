package com.chin.alb.juv.di.address

import com.chin.data.gateways.address.AddressLocalGateway
import com.chin.data.gateways.address.AddressLocalGatewayImpl
import com.chin.domain.usecase.GetAddressListUseCase
import com.chin.domain.usecase.SaveSelectedAddressUseCase
import com.chin.domain.usecase.address.GetAddressListUseCaseImpl
import com.chin.domain.usecase.address.SaveSelectedAddressUseCaseImpl
import com.chin.presentation.address.AddressPresenter
import com.chin.presentation.address.AddressPresenterImpl
import com.chin.alb.di.main.AddressScope
import dagger.Module
import dagger.Provides

@Module
class AddressModule {

    @Provides
    @AddressScope
    fun providesAddressPresenter(impl: AddressPresenterImpl):AddressPresenter = impl

    @Provides
    @AddressScope
    fun providesAddressLocalGateway(impl: AddressLocalGatewayImpl): AddressLocalGateway = impl

    @Provides
    @AddressScope
    fun providesGetAddressListUseCase(impl: GetAddressListUseCaseImpl): GetAddressListUseCase = impl

    @Provides
    @AddressScope
    fun providesSaveSelectedAddressUseCase(impl: SaveSelectedAddressUseCaseImpl): SaveSelectedAddressUseCase = impl

}