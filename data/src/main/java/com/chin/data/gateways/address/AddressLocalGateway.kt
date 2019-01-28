package com.chin.data.gateways.address

import com.chin.data.entities.address.AddressModel

interface AddressLocalGateway {

    fun getAddresses(): List<AddressModel>

    fun getAddress(address: String): AddressModel?

    fun updateAddress(address: AddressModel)

    fun deleteOldAddresses()

    fun insertAddress(address: AddressModel): Any
}