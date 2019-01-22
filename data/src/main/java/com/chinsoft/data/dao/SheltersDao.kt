package com.chinsoft.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chinsoft.data.entities.ShelterModel


@Dao
interface SheltersDao {

    companion object {
        const val TABLE_NAME = "shelters"
        const val COLUMN_ID = "id"
    }

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<ShelterModel>

    @Insert
    fun insertAll(shelterList: List<ShelterModel>)

    @Delete
    fun delete(item: ShelterModel)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

}