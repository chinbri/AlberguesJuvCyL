package com.example.chin.domain.entities

import com.example.chin.data.entities.ShelterModel

data class ShelterEntity (
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
        fun fromModel(model: ShelterModel) = ShelterEntity(
            model.identificador,
            model.t_tulo ?: "---",
            model.descripci_n ?: "---",
            model.carta ?: "",
            model.enlace_al_contenido ?: "",
            model.posicion?.coordinates?.get(0) ?: 0.0,
            model.posicion?.coordinates?.get(1) ?: 0.0,
            model.distance
        )
    }

}