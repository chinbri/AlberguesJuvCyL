package com.chinsoft.domain.usecase.main

import com.chinsoft.data.gateways.MainLocalGateway
import com.chinsoft.domain.entities.CachedRechargePointsDataEntity
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.domain.usecase.GetCachedRechargePointsUseCase
import com.chinsoft.domain.usecase.InternalErrorNotification
import com.chinsoft.domain.usecase.UseCaseResponse
import com.chinsoft.domain.util.PreferenceUtils
import kotlinx.coroutines.Job
import javax.inject.Inject

class GetCachedRechargePointsUseCaseImpl @Inject constructor(
    override val job: Job,
    private val mainLocalGateway: MainLocalGateway,
    private val preferenceUtils: PreferenceUtils
): GetCachedRechargePointsUseCase {

    override suspend fun run(input: Unit): UseCaseResponse<CachedRechargePointsDataEntity> {

        try {

            return UseCaseResponse(
                output = CachedRechargePointsDataEntity(
                    mainLocalGateway.getRechargePoints()
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