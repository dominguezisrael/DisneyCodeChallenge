package com.personal.disney.entity

import com.google.gson.annotations.SerializedName

data class ForecastData(
        @SerializedName("properties") val properties: ForecastProperties
)

data class ForecastProperties(
        @SerializedName("periods") val periods: List<ForecastPeriod>
)

data class ForecastPeriod(
        @SerializedName("startTime") val startTime: String,
        @SerializedName("temperature") val temperature: Temperature,
        @SerializedName("shortForecast") val shortForecast: String
)

class Temperature(
        @SerializedName("value") val value: Double
)