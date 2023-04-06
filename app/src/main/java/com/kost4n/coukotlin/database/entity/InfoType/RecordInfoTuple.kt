package com.kost4n.coukotlin.database.entity.InfoType

import androidx.room.ColumnInfo

data class RecordInfoTuple(
    val id: Long,
    @ColumnInfo(name = "date_name") val date: String,
    val up_pressure: Int,
    val dw_pressure: Int,
    val pulse: Int
)