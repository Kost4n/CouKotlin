package com.kost4n.coukotlin.database.dao

import androidx.room.*
import com.kost4n.coukotlin.database.entity.RecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecord(recordEntity: RecordEntity)

    @Query("SELECT * FROM records ORDER BY date DESC")
    fun getRecords(): Flow<List<RecordEntity>>

    @Delete
    suspend fun deleteRecord(recordEntity: RecordEntity)
}