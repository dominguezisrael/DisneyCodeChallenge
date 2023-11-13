package com.personal.disney.controller

import com.personal.disney.entity.ForecastResponse
import com.personal.disney.service.ForecastServiceRetrofit
import com.personal.disney.service.ForecastServiceWebClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ForecastController {
    @Autowired
    private lateinit var forecastServiceWebClient: ForecastServiceWebClient

    @Autowired
    private lateinit var forecastServiceRetrofit: ForecastServiceRetrofit

    @GetMapping
    fun getForecastWithWebClient(): Mono<ForecastResponse>? {
        return forecastServiceWebClient.getForecastData()
    }

    @GetMapping("/withRetrofit")
    fun getForecastWithRetrofit(): Mono<ForecastResponse>? {
        return forecastServiceRetrofit.getForecastData()
    }
}