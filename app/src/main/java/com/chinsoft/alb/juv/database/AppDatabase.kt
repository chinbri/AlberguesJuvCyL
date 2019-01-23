package com.chinsoft.alb.juv.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chinsoft.data.dao.SheltersDao
import com.chinsoft.data.dao.address.AddressDao
import com.chinsoft.data.entities.ShelterModel
import com.chinsoft.data.entities.address.AddressModel

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