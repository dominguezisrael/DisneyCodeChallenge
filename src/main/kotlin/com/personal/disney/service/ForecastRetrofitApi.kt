package com.personal.disney.service

import com.personal.disney.entity.ForecastData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ForecastRetrofitApi {
    @Headers(
            "accept: application/geo+json",
            "Feature-Flags: forecast_temperature_qv"
    )
    @GET("/gridpoints/MLB/33,70/forecast/")
    fun getForecast(@Query("units") units: String): Call<ForecastData>
}