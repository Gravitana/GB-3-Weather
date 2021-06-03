package com.example.gb_3_weather.viewmodel

import com.example.gb_3_weather.model.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}