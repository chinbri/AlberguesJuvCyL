package com.chin.data.gateways

import com.chin.data.dao.SheltersDao
import com.chin.data.entities.ShelterModel
import javax.inject.Inject

class MainLocalGatewayImpl @Inject constructor(
    val sheltersDao: SheltersDao
): MainLocalGateway {

    override suspend fun getShelterList() = sheltersDao.getAll()

    override suspend fun insertAll(points: List<ShelterModel>) {
        sheltersDao.insertAll(points)
    }

    override suspend fun deleteItem(item: ShelterModel) = sheltersDao.delete(item)

    override suspend fun deleteAll() {
        sheltersDao.deleteAll()
    }

}