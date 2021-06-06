package com.example.gb_3_weather.Marvel.Interface

import com.example.gb_3_weather.Marvel.Model.Movie
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServieces {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>
}