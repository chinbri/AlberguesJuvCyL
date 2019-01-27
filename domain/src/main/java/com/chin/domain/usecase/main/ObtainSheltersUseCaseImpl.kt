package com.chin.domain.usecase.main

import com.chin.data.entities.ShelterModel
import com.chin.data.gateways.MainLocalGateway
import com.chin.data.gateways.MainNetworkGateway
import com.chin.domain.entities.ObtainSheltersInputEntity
import com.chin.domain.entities.ShelterEntity
import com.chin.domain.usecase.*
import com.chin.domain.util.LocationUtils
import com.chin.domain.util.PreferenceUtils
import kotlinx.coroutines.Job
import java.io.IOException
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
            val shelterList = obtainShelterList(input)


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
        }catch (e: IOException){
            println(e.message)

            return UseCaseResponse(NetworkErrorNotification())
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