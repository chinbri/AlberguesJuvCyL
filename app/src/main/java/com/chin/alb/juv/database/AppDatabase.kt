package com.chin.alb.juv.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chin.data.dao.SheltersDao
import com.chin.data.dao.address.AddressDao
import com.chin.data.entities.ShelterModel
import com.chin.data.entities.address.AddressModel

@Database(entities = [ShelterModel::class, AddressModel::class], version = 1, exportSchema = false)
@TypeConverters(
    CoordinateTypeConverter::class,
    DateTypeConverter::class
)
abstract class AppDatabase : RoomDatabase()  {

    companion object {
        const val DATABASE_NAME = "sheltersDB"
    }

    abstract fun sheltersDao(): SheltersDao

    abstract fun addressDao(): AddressDao
}