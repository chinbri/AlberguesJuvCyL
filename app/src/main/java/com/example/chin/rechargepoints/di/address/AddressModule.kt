package com.example.chin.rechargepoints.di.address

import com.example.chin.data.gateways.address.AddressLocalGateway
import com.example.chin.data.gateways.address.AddressLocalGatewayImpl
import com.example.chin.domain.usecase.GetAddressListUseCase
import com.example.chin.domain.usecase.SaveSelectedAddressUseCase
import com.example.chin.domain.usecase.address.GetAddressListUseCaseImpl
import com.example.chin.domain.usecase.address.SaveSelectedAddressUseCaseImpl
import com.example.chin.presentation.address.AddressPresenter
import com.example.chin.presentation.address.AddressPresenterImpl
import com.example.chin.rechargepoints.di.main.AddressScope
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