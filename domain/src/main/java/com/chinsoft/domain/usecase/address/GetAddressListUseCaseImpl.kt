package com.chinsoft.domain.usecase.address

import com.chinsoft.data.gateways.address.AddressLocalGateway
import com.chinsoft.domain.usecase.GetAddressListUseCase
import com.chinsoft.domain.usecase.UseCaseResponse
import kotlinx.coroutines.Job
import javax.inject.Inject

class GetAddressListUseCaseImpl @Inject constructor(
    override val job: Job,
    private val addressLocalGateway: AddressLocalGateway
) : GetAddressListUseCase {

    override suspend fun run(input: Unit): UseCaseResponse<List<String>> {

        addressLocalGateway.deleteOldAddresses()

        return UseCaseResponse(output = addressLocalGateway.getAddresses().map {
            it.address
        })

    }

}