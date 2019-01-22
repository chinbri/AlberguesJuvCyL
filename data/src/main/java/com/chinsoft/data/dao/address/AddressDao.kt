package com.chinsoft.data.dao.address

import androidx.room.*
import com.chinsoft.data.entities.address.AddressModel


@Dao
interface AddressDao {

    companion object {
        const val TABLE_NAME = "addressData"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_DATE = "lastUseDate"

        const val DAYS_SAVED = 15
    }

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<AddressModel>

    @Delete
    fun delete(item: AddressModel)

    @Query("DELETE FROM $TABLE_NAME WHERE $COLUMN_DATE <= :compareDate")
    fun deleteOld(compareDate: Long)

    @Update
    fun update(item: AddressModel)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ADDRESS LIKE :address LIMIT 1")
    fun get(address: String): AddressModel?

    @Insert
    fun insert(address: AddressModel)


}