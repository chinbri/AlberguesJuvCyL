package com.example.chin.rechargepoints.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chin.data.dao.RechargePointsDao
import com.example.chin.data.dao.address.AddressDao
import com.example.chin.data.entities.RechargablePointModel
import com.example.chin.data.entities.address.AddressModel

@Database(entities = [RechargablePointModel::class, AddressModel::class], version = 1)
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