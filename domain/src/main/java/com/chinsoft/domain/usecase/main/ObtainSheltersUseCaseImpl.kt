package com.chinsoft.domain.usecase.main

import com.chinsoft.data.entities.ShelterModel
import com.chinsoft.data.gateways.MainLocalGateway
import com.chinsoft.data.gateways.MainNetworkGateway
import com.chinsoft.domain.entities.ObtainSheltersInputEntity
import com.chinsoft.domain.entities.ShelterEntity
import com.chinsoft.domain.usecase.AddressNotFoundNotification
import com.chinsoft.domain.usecase.InternalErrorNotification
import com.chinsoft.domain.usecase.ObtainSheltersUseCase
import com.chinsoft.domain.usecase.UseCaseResponse
import com.chinsoft.domain.util.LocationUtils
import com.chinsoft.domain.util.PreferenceUtils
import kotlinx.coroutines.Job
import javax.inject.Inject
import javax.inject.Named

class ObtainSheltersUseCaseImpl @Inject constructor(override val job: Job,
                                                    private val mainLocalGateway: MainLocalGateway,
                                                    private val mainNetworkGateway: MainNetworkGateway,
                                                    private val locationUtils: LocationUtils,
                                                    private val preferenceUtils: PreferenceUtils,
                                                    @Named("datosAbiertosApiKey") private val datosAbiertosApiKey: String
): ObtainSheltersUseCase {

    override suspend fun run(input: ObtainSheltersInputEntity): UseCaseResponse<List<ShelterEntity>> {

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
            val shelterList = obtainShelterList(input).filter {
                isValidPosition(it.posicion?.coordinates?.get(1) ?: 0.0, it.posicion?.coordinates?.get(0) ?: 0.0)
            }

            mainLocalGateway.deleteAll()

            shelterList.forEach {
                val longitude = it.posicion?.coordinates?.get(0) ?: 0.0
                val latitude = it.posicion?.coordinates?.get(1) ?: 0.0
                it.distance = locationUtils.getDistance(latitude, longitude, input.latitude, input.longitude)
            }
            mainLocalGateway.insertAll(shelterList)

            return UseCaseResponse(output = shelterList
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

    private suspend fun obtainShelterList(input: ObtainSheltersInputEntity): List<ShelterModel>{

        if(input.searchAll){

            return mainNetworkGateway.getAllShelters(
                datosAbiertosApiKey)

        }else{
            return mainNetworkGateway.getSheltersFromPosition(
                input.latitude,
                input.longitude,
                preferenceUtils.getSearchRatio() * 1000L,
                datosAbiertosApiKey)
        }
    }

}