package com.example.chin.data.gateways.address

import com.example.chin.data.entities.address.AddressModel

interface AddressLocalGateway {

    fun getAddresses(): List<AddressModel>

    fun getAddress(address: String): AddressModel?

    fun updateAddress(address: AddressModel)

    fun deleteOldAddresses()

    fun insertAddress(address: AddressModel): Any
}