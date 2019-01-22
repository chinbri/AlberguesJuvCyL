package com.chinsoft.domain.usecase.main

import com.chinsoft.data.entities.ShelterModel
import com.chinsoft.data.gateways.MainLocalGateway
import com.chinsoft.data.gateways.MainNetworkGateway
import com.chinsoft.domain.entities.ObtainRechargePointsInputEntity
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.domain.usecase.AddressNotFoundNotification
import com.chinsoft.domain.usecase.InternalErrorNotification
import com.chinsoft.domain.usecase.ObtainRechargePointsUseCase
import com.chinsoft.domain.usecase.UseCaseResponse
import com.chinsoft.domain.util.LocationUtils
import com.chinsoft.domain.util.PreferenceUtils
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
            val rechargePoints = obtainRechargePoints(input).filter {
                isValidPosition(it.posicion?.coordinates?.get(1) ?: 0.0, it.posicion?.coordinates?.get(0) ?: 0.0)
            }

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

    /**
     * latitude and longitude bounds of castile-leon territory, in case of invalid position
     */
    private fun isValidPosition(latitude: Double, longitude: Double) =
        latitude > 39.5 && latitude < 43 && longitude > -8 && longitude < 0

    private suspend fun obtainRechargePoints(input: ObtainRechargePointsInputEntity): List<ShelterModel>{

        if(input.searchAll){

            return mainNetworkGateway.getAllRechargePoints(
                datosAbiertosApiKey)

        }else{
            return mainNetworkGateway.getRechargePointsFromPosition(
                input.latitude,
                input.longitude,
                preferenceUtils.getSearchRatio() * 1000L,
                datosAbiertosApiKey)
        }
    }

}