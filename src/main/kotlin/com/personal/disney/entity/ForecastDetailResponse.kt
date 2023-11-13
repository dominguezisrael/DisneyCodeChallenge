package com.personal.disney.entity

data class ForecastDetailResponse(
    val day_name: String,
    val temp_high_celsius: Double,
    val forecast_blurp: String
)