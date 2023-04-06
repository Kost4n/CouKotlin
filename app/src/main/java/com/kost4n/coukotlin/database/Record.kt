package com.kost4n.coukotlin.database

import com.kost4n.coukotlin.database.entity.RecordEntity

data class Record(
    val datetId: Long,
    val up_pressure: Int,
    val dw_pressure: Int,
    val pulse: Int
) {

    fun toRecordEntity(): RecordEntity = RecordEntity(
        id = 0,
        date_id = datetId,
        up_pressure = up_pressure,
        dw_pressure = dw_pressure,
        pulse = pulse
    )
}
