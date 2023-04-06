package com.kost4n.coukotlin.database.entity

import androidx.room.*

@Entity(
    tableName = "records",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = DateEntity::class,
            parentColumns = ["id"],
            childColumns = ["date_id"]
        )
    ]
)
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "date_id") val date_id: Long,
    @ColumnInfo(name = "up_pressure") val up_pressure: Int,
    @ColumnInfo(name = "dw_pressure") val dw_pressure: Int,
    @ColumnInfo(name = "pulse") val pulse: Int
)