package com.kost4n.coukotlin.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "records")
data class RecordEntity(
    @PrimaryKey
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "up_pressure") val up_pressure: Int,
    @ColumnInfo(name = "dw_pressure") val dw_pressure: Int,
    @ColumnInfo(name = "pulse") val pulse: Int
    )