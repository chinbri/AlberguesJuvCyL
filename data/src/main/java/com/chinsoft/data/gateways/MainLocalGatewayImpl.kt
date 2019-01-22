package com.chinsoft.data.gateways

import com.chinsoft.data.dao.RechargePointsDao
import com.chinsoft.data.entities.ShelterModel
import javax.inject.Inject

class MainLocalGatewayImpl @Inject constructor(
    val rechargePointsDao: RechargePointsDao
): MainLocalGateway {

    override suspend fun getRechargePoints() = rechargePointsDao.getAll()

    override suspend fun insertAll(points: List<ShelterModel>) {
        rechargePointsDao.insertAll(points)
    }

    override suspend fun deleteItem(item: ShelterModel) = rechargePointsDao.delete(item)

    override suspend fun deleteAll() {
        rechargePointsDao.deleteAll()
    }

}