package com.example.chin.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chin.data.dao.RechargePointsDao

@Entity(tableName = RechargePointsDao.TABLE_NAME)
data class RechargablePointModel(
    val calle: String?,
    val codigopostal: String?,
    val datospersonales: String?,
    val enlace_al_contenido: String?,
    val fechamodificacion: String?,

    val id_localidad: String?,
    @PrimaryKey
    @ColumnInfo(name = RechargePointsDao.COLUMN_ID)
    val identificador: String,
    val localidad: String?,
    val nombre_del_organismo: String?,
    @Embedded
    val posicion: RechargePointsPositionModel?,
    var distance: Float
)
