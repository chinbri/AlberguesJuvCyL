package com.chin.domain.usecase.main

import com.chin.data.gateways.MainLocalGateway
import com.chin.domain.entities.CachedSheltersDataEntity
import com.chin.domain.entities.ShelterEntity
import com.chin.domain.usecase.GetCachedSheltersUseCase
import com.chin.domain.usecase.InternalErrorNotification
import com.chin.domain.usecase.UseCaseResponse
import com.chin.domain.util.PreferenceUtils
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