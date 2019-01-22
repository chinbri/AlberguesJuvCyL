package com.chinsoft.domain.usecase

import com.chinsoft.domain.entities.CachedRechargePointsDataEntity
import com.chinsoft.domain.entities.ObtainRechargePointsInputEntity
import com.chinsoft.domain.entities.ShelterEntity

interface ObtainRechargePointsUseCase: UseCase<ObtainRechargePointsInputEntity, List<ShelterEntity>>

interface GetCachedRechargePointsUseCase: UseCase<Unit, CachedRechargePointsDataEntity>

interface GetAddressListUseCase: UseCase<Unit, List<String>>

interface SaveSelectedAddressUseCase: UseCase<String, Unit>