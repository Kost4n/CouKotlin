package com.kost4n.coukotlin.database.repository

import com.kost4n.coukotlin.database.dao.RecordDao
import com.kost4n.coukotlin.database.entity.InfoType.RecordInfoTuple
import com.kost4n.coukotlin.database.entity.RecordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecordRepository(private val recordDao: RecordDao) {
    suspend fun insertNewStatisticData(recordEntity: RecordEntity) {
        withContext(Dispatchers.IO) {
            recordDao.insertNewRecordData(recordEntity)
        }
    }

    suspend fun getAllStatisticData(): List<RecordInfoTuple> {
        return withContext(Dispatchers.IO) {
            return@withContext recordDao.getAllRecordsData()
        }
    }

    suspend fun removeStatisticDataById(id: Long) {
        withContext(Dispatchers.IO) {
            recordDao.deleteRecordDataById(id)
        }
    }

}