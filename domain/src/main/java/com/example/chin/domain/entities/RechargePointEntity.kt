package com.example.chin.domain.entities

import com.example.chin.data.entities.RechargablePointModel

data class RechargePointEntity (
    val id: String,
    val name: String,
    val address: String,
    val description: String,
    val url: String,
    val longitude: Double,
    val latitude: Double,
    val distance: Float
){
    companion object {
        fun fromModel(model: RechargablePointModel) = RechargePointEntity(
            model.identificador,
            model.datospersonales ?: "---",
            model.calle ?: "---",
            model.nombre_del_organismo ?: "",
            model.enlace_al_contenido ?: "",
            model.posicion?.coordinates?.get(0) ?: 0.0,
            model.posicion?.coordinates?.get(1) ?: 0.0,
            model.distance
        )
    }

}