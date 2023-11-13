package com.personal.disney.service

import com.personal.disney.entity.ForecastData
import com.personal.disney.entity.ForecastDetailResponse
import com.personal.disney.entity.ForecastResponse
import com.personal.disney.service.ForecastServiceUtils.formatTo2Decimals
import com.personal.disney.service.ForecastServiceUtils.getDayName
import com.personal.disney.service.ForecastServiceUtils.isTodayDate
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class ForecastServiceWebClient(webClientBuilder: WebClient.Builder) {
    private var webClient: WebClient? = null

    init {
        webClient = webClientBuilder.baseUrl("https://api.weather.gov")
            .build()
    }

    fun getForecastData(): Mono<ForecastResponse>? {
        return webClient?.get()
            ?.uri("/gridpoints/MLB/33,70/forecast?units=us")
            ?.header("accept", "application/geo+json")
            ?.header("Feature-Flags", "forecast_temperature_qv")
            ?.retrieve()
            ?.bodyToMono(ForecastData::class.java)
            ?.map { it ->
                it.properties.periods.filter { isTodayDate(it) }
                    .map {
                        ForecastDetailResponse(
                            getDayName(it),
                            formatTo2Decimals(it.temperature.value),
                            it.shortForecast
                        )
                    }
            }
            ?.map { ForecastResponse(it) }
    }
}