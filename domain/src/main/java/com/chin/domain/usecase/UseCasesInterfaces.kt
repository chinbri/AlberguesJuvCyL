package com.chin.domain.usecase

import com.chin.domain.entities.CachedSheltersDataEntity
import com.chin.domain.entities.ObtainSheltersInputEntity
import com.chin.domain.entities.ShelterEntity

interface ObtainSheltersUseCase: UseCase<ObtainSheltersInputEntity, List<ShelterEntity>>

interface GetCachedSheltersUseCase: UseCase<Unit, CachedSheltersDataEntity>

interface GetAddressListUseCase: UseCase<Unit, List<String>>

interface SaveSelectedAddressUseCase: UseCase<String, Unit>