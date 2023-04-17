package com.kost4n.coukotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kost4n.coukotlin.database.converter.RecordConverters
import com.kost4n.coukotlin.database.dao.RecordDao
import com.kost4n.coukotlin.database.entity.RecordEntity

@Database(
    version = 1,
    entities = [
        RecordEntity::class
    ],
    exportSchema = true
)
@TypeConverters(RecordConverters::class)
abstract class RecordDatabase : RoomDatabase() {
    abstract fun getRecordDao(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null

        fun getDatabase(context: Context): RecordDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): RecordDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                RecordDatabase::class.java,
                "records_database"
            ).build()
        }
    }
}

