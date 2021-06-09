package com.example.gb_3_weather.model

data class FactDTO(
        val temp: Int?,
        val feels_like: Int?,
        val condition: String?
)

/*
"fact": {
        "temp": 20,
        "feels_like": 21,
        "icon": "ovc",
        "condition": "overcast",
        "wind_speed": 2,
        "wind_gust": 5.9,
        "wind_dir": "n",
        "pressure_mm": 745,
        "pressure_pa": 994,
        "humidity": 83,
        "daytime": "d",
        "polar": false,
        "season": "summer",
        "obs_time": 1470214800
},
*/
