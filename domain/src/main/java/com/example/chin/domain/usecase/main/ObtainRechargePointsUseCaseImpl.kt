package com.example.chin.domain.usecase.main

import com.example.chin.data.gateways.MainLocalGateway
import com.example.chin.data.gateways.MainNetworkGateway
import com.example.chin.domain.entities.ObtainRechargePointsInputEntity
import com.example.chin.domain.entities.ShelterEntity
import com.example.chin.domain.usecase.AddressNotFoundNotification
import com.example.chin.domain.usecase.InternalErrorNotification
import com.example.chin.domain.usecase.ObtainRechargePointsUseCase
import com.example.chin.domain.usecase.UseCaseResponse
import com.example.chin.domain.util.LocationUtils
import com.example.chin.domain.util.PreferenceUtils
import kotlinx.coroutines.Job
import javax.inject.Inject
import javax.inject.Named

class ObtainRechargePointsUseCaseImpl @Inject constructor(override val job: Job,
                                                          private val mainLocalGateway: MainLocalGateway,
                                                          private val mainNetworkGateway: MainNetworkGateway,
                                                          private val locationUtils: LocationUtils,
                                                          private val preferenceUtils: PreferenceUtils,
                                                          @Named("datosAbiertosApiKey") private val datosAbiertosApiKey: String
): ObtainRechargePointsUseCase {

    override suspend fun run(input: ObtainRechargePointsInputEntity): UseCaseResponse<List<ShelterEntity>> {

        if(input.address?.isNotEmpty() == true){

            val geoPoint = locationUtils.getLocationFromAddress(input.address)

            if(geoPoint == null){

                return UseCaseResponse(AddressNotFoundNotification())

            }else{
                preferenceUtils.saveLastAddress(input.address)

                input.latitude = geoPoint.latitude
                input.longitude = geoPoint.longitude
            }

        }else{

            preferenceUtils.saveLastAddress(null)

        }

        try{
            val rechargePoints = mainNetworkGateway.getRechargePoints(
                input.latitude,
                input.longitude,
                preferenceUtils.getSearchRatio() * 1000L,
                datosAbiertosApiKey
            )

            mainLocalGateway.deleteAll()

            rechargePoints.forEach {
                val longitude = it.posicion?.coordinates?.get(0) ?: 0.0
                val latitude = it.posicion?.coordinates?.get(1) ?: 0.0
                it.distance = locationUtils.getDistance(latitude, longitude, input.latitude, input.longitude)
            }
            mainLocalGateway.insertAll(rechargePoints)

            return UseCaseResponse(output = rechargePoints
                .sortedByDescending {
                    -it.distance
                }
                .map {
                    ShelterEntity.fromModel(it)
                })
        }catch (e: Exception){
            println(e.message)

            return UseCaseResponse(InternalErrorNotification())
        }


    }

}