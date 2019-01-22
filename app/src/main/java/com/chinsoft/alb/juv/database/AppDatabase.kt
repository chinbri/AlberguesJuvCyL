package com.chinsoft.alb.juv.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chinsoft.data.dao.RechargePointsDao
import com.chinsoft.data.dao.address.AddressDao
import com.chinsoft.data.entities.ShelterModel
import com.chinsoft.data.entities.address.AddressModel

@Database(entities = [ShelterModel::class, AddressModel::class], version = 1)
@TypeConverters(
    CoordinateTypeConverter::class,
    DateTypeConverter::class
)
abstract class AppDatabase : RoomDatabase()  {

    companion object {
        const val DATABASE_NAME = "rechargePointsDB"
    }

    abstract fun rechargePointsDao(): RechargePointsDao

    abstract fun addressDao(): AddressDao
}