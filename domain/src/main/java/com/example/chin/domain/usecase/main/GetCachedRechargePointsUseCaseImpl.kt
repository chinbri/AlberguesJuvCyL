package com.example.chin.domain.usecase.main

import com.example.chin.data.gateways.MainLocalGateway
import com.example.chin.domain.entities.CachedRechargePointsDataEntity
import com.example.chin.domain.entities.RechargePointEntity
import com.example.chin.domain.usecase.GetCachedRechargePointsUseCase
import com.example.chin.domain.usecase.InternalErrorNotification
import com.example.chin.domain.usecase.UseCaseResponse
import com.example.chin.domain.util.PreferenceUtils
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
                            RechargePointEntity.fromModel(it)
                        },
                    preferenceUtils.getLastAddress()
                )
            )

        }catch (e: Exception){
            return UseCaseResponse(InternalErrorNotification())
        }

    }

}