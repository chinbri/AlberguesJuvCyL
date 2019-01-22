package com.chinsoft.alb.juv.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class CoordinateTypeConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Double> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Double>>() {

        }.type

        return gson.fromJson<List<Double>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Double>): String {
        return gson.toJson(someObjects)
    }
}
