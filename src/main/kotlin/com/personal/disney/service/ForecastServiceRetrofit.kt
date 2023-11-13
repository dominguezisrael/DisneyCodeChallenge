package com.personal.disney.service

import com.personal.disney.entity.ForecastDetailResponse
import com.personal.disney.entity.ForecastResponse
import com.personal.disney.service.ForecastServiceUtils.formatTo2Decimals
import com.personal.disney.service.ForecastServiceUtils.getDayName
import com.personal.disney.service.ForecastServiceUtils.isTodayDate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class ForecastServiceRetrofit {
    fun getForecastData(): Mono<ForecastResponse>? {
        val forecastRetrofitApi = ForecastRetrofitServiceBuilder.buildService(ForecastRetrofitApi::class.java)

        val apiResponse = forecastRetrofitApi.getForecast("us").execute()
        val apiResponseBody = apiResponse.body()

        val forecastDetails = apiResponseBody?.properties?.periods
            ?.filter { isTodayDate(it) }
            ?.map {
                ForecastDetailResponse(
                    getDayName(it),
                    formatTo2Decimals(it.temperature.value),
                    it.shortForecast
                )
            }
            ?.toList()

        val forecastResponse = ForecastResponse(forecastDetails!!)

        return forecastResponse.toMono()
    }
}