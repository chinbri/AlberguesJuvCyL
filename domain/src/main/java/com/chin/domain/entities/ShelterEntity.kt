package com.chin.domain.entities

import com.chin.data.entities.ShelterModel

data class ShelterEntity (
    val id: String,
    val name: String,
    val opened: String?,
    val description: String,
    val url: String?,
    val longitude: Double,
    val latitude: Double,
    val images: String?,
    val mainImage: String?,
    val distance: Float
){
    companion object {
        fun fromModel(model: ShelterModel) = ShelterEntity(
            model.identificador,
            model.t_tulo ?: "---",
            model.periodoabierto,
            model.descripci_n ?: "",
            model.enlace_al_contenido,
            model.posicion?.coordinates?.get(0) ?: 0.0,
            model.posicion?.coordinates?.get(1) ?: 0.0,
            model.imagenes_asociadas,
            model.imagen,
            model.distance
        )
    }

    fun obtainImages(): List<String>{
        val wholeList = emptyList<String>().toMutableList()

        if(!mainImage.isNullOrEmpty()){
            wholeList.add(mainImage)
        }
        if(!images.isNullOrEmpty()){
            wholeList.addAll(images.split(", "))
        }

        return wholeList
    }

}