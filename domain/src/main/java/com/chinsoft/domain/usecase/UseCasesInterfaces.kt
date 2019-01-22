package com.chinsoft.domain.usecase

import com.chinsoft.domain.entities.CachedSheltersDataEntity
import com.chinsoft.domain.entities.ObtainSheltersInputEntity
import com.chinsoft.domain.entities.ShelterEntity

interface ObtainSheltersUseCase: UseCase<ObtainSheltersInputEntity, List<ShelterEntity>>

interface GetCachedSheltersUseCase: UseCase<Unit, CachedSheltersDataEntity>

interface GetAddressListUseCase: UseCase<Unit, List<String>>

interface SaveSelectedAddressUseCase: UseCase<String, Unit>