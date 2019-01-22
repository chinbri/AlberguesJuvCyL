package com.chinsoft.domain.usecase.main

import com.chinsoft.data.gateways.MainLocalGateway
import com.chinsoft.domain.entities.CachedSheltersDataEntity
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.domain.usecase.GetCachedSheltersUseCase
import com.chinsoft.domain.usecase.InternalErrorNotification
import com.chinsoft.domain.usecase.UseCaseResponse
import com.chinsoft.domain.util.PreferenceUtils
import kotlinx.coroutines.Job
import javax.inject.Inject

class GetCachedSheltersUseCaseImpl @Inject constructor(
    override val job: Job,
    private val mainLocalGateway: MainLocalGateway,
    private val preferenceUtils: PreferenceUtils
): GetCachedSheltersUseCase {

    override suspend fun run(input: Unit): UseCaseResponse<CachedSheltersDataEntity> {

        try {

            return UseCaseResponse(
                output = CachedSheltersDataEntity(
                    mainLocalGateway.getShelterList()
                        .sortedByDescending { -it.distance }
                        .map {
                            ShelterEntity.fromModel(it)
                        },
                    preferenceUtils.getLastAddress()
                )
            )

        }catch (e: Exception){
            return UseCaseResponse(InternalErrorNotification())
        }

    }

}