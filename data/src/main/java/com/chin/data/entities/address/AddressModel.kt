package com.chin.data.entities.address

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chin.data.dao.address.AddressDao
import java.util.*

@Entity(tableName = AddressDao.TABLE_NAME)
data class AddressModel(
    @PrimaryKey
    @ColumnInfo(name = AddressDao.COLUMN_ADDRESS)
    val address: String,
    @ColumnInfo(name = AddressDao.COLUMN_DATE)
    val lastUseDate: Date
)
