package com.example.gb_3_weather.Marvel.Common

import com.example.gb_3_weather.Marvel.Interface.RetrofitServieces
import com.example.gb_3_weather.Marvel.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitServieces
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServieces::class.java)
}