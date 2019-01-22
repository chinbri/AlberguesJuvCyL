package com.chinsoft.data.gateways.address

import com.chinsoft.data.dao.address.AddressDao
import com.chinsoft.data.entities.address.AddressModel
import java.util.*
import javax.inject.Inject

class AddressLocalGatewayImpl @Inject constructor(private val addressDao: AddressDao): AddressLocalGateway {

    override fun getAddresses(): List<AddressModel> = addressDao.getAll()

    override fun getAddress(address: String) = addressDao.get(address)

    override fun updateAddress(address: AddressModel) = addressDao.update(address)

    override fun insertAddress(address: AddressModel) = addressDao.insert(address)

    override fun deleteOldAddresses() = addressDao.deleteOld(getDaysAgo())

    private fun getDaysAgo(): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -AddressDao.DAYS_SAVED)

        return calendar.timeInMillis
    }

}