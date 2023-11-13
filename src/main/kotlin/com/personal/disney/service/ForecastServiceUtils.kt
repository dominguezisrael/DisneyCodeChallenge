package com.personal.disney.service

import com.personal.disney.entity.ForecastPeriod
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.floor

object ForecastServiceUtils {
    fun isTodayDate(forecastPeriod: ForecastPeriod): Boolean {
        val offsetDateTime = OffsetDateTime.parse(forecastPeriod.startTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)

        return offsetDateTime.toLocalDate().isEqual(LocalDate.now())
    }

    fun getDayName(forecastPeriod: ForecastPeriod): String {
        val offsetDateTime = OffsetDateTime.parse(forecastPeriod.startTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)

        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = simpleDateFormat.parse(offsetDateTime.toString())

        return SimpleDateFormat("EEEE").format(date)
    }

    fun formatTo2Decimals(doubleValue: Double): Double {
        return floor(doubleValue * 100) / 100
    }
}