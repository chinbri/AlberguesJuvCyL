package com.chinsoft.alb.juv.di.address

import com.chinsoft.data.gateways.address.AddressLocalGateway
import com.chinsoft.data.gateways.address.AddressLocalGatewayImpl
import com.chinsoft.domain.usecase.GetAddressListUseCase
import com.chinsoft.domain.usecase.SaveSelectedAddressUseCase
import com.chinsoft.domain.usecase.address.GetAddressListUseCaseImpl
import com.chinsoft.domain.usecase.address.SaveSelectedAddressUseCaseImpl
import com.chinsoft.presentation.address.AddressPresenter
import com.chinsoft.presentation.address.AddressPresenterImpl
import com.chinsoft.alb.di.main.AddressScope
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