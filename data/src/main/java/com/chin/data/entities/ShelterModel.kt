package com.chin.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chin.data.dao.SheltersDao

@Entity(tableName = SheltersDao.TABLE_NAME)
data class ShelterModel(
    @PrimaryKey
    @ColumnInfo(name = SheltersDao.COLUMN_ID)
    val identificador: String,
    val capacidad: String?,
    val carta: String?,
    val descripci_n: String?,
    val enlace_al_contenido: String?,
    val imagen: String?,
    val imagenes_asociadas: String?,
    val periodoabierto: String?,
    val abierto: String?,
    val actividades: String?,
    val areaocio: String?,
    val provincia: String?,
    val regimenprecio: String?,
    val t_tulo: String?,
    @Embedded
    val posicion: PositionModel?,
    var distance: Float
)
