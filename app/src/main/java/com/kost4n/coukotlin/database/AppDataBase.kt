package com.kost4n.coukotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kost4n.coukotlin.database.dao.RecordDao
import com.kost4n.coukotlin.database.entity.DateEntity
import com.kost4n.coukotlin.database.entity.RecordEntity

@Database(
    version = 1,
    entities = [
        DateEntity::class,
        RecordEntity::class
    ]
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getRecordDao(): RecordDao
}