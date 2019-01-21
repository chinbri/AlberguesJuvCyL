package com.example.chin.domain.usecase

import com.example.chin.domain.entities.CachedRechargePointsDataEntity
import com.example.chin.domain.entities.ObtainRechargePointsInputEntity
import com.example.chin.domain.entities.RechargePointEntity

interface ObtainRechargePointsUseCase: UseCase<ObtainRechargePointsInputEntity, List<RechargePointEntity>>

interface GetCachedRechargePointsUseCase: UseCase<Unit, CachedRechargePointsDataEntity>

interface GetAddressListUseCase: UseCase<Unit, List<String>>

interface SaveSelectedAddressUseCase: UseCase<String, Unit>