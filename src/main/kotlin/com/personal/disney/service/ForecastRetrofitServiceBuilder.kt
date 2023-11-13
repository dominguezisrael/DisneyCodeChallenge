package com.personal.disney.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ForecastRetrofitServiceBuilder {
    private val httpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weather.gov")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()

    /**
     * Builds retrofit service.
     *
     * @param service service definition.
     */
    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}