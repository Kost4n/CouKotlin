package com.kost4n.coukotlin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kost4n.coukotlin.database.entity.InfoType.RecordInfoTuple
import com.kost4n.coukotlin.database.entity.RecordEntity

@Dao
interface RecordDao {
    @Insert(entity = RecordEntity::class)
    fun insertNewRecordData(recordEntity: RecordEntity)

    @Query("DELETE FROM records WHERE id = :recordId")
    fun deleteRecordDataById(recordId: Long)

    @Query("SELECT * FROM statistic\n" +
            "INNER JOIN date_recording ON records.date_id = date.id\n")
    fun getAllRecordsData(): List<RecordInfoTuple>
}