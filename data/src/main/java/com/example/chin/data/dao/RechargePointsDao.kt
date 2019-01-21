package com.example.chin.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.chin.data.entities.ShelterModel


@Dao
interface RechargePointsDao {

    companion object {
        const val TABLE_NAME = "rechargePoints"
        const val COLUMN_ID = "id"
    }

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<ShelterModel>
//
//    @Query("SELECT * FROM $TABLE_NAME WHERE name IN (:userNames)")
//    fun loadAllByIds(userNames: StringArray): List<ShoppingLocalEntity>
//
//    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE :name LIMIT 1")
//    fun findByName(name: String): ShoppingLocalEntity

    @Insert
    fun insertAll(rechargePoints: List<ShelterModel>)

    @Delete
    fun delete(item: ShelterModel)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

}