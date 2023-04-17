package com.kost4n.coukotlin.database.converter

import androidx.room.TypeConverter
import java.util.*

class RecordConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date?): Long? {
        return date?.time
    }
}