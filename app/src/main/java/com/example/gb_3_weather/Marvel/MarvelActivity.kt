package com.example.gb_3_weather.Marvel

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_3_weather.Marvel.Adapter.MarvelMovieAdapter
import com.example.gb_3_weather.Marvel.Common.Common
import com.example.gb_3_weather.Marvel.Interface.RetrofitServieces
import com.example.gb_3_weather.Marvel.Model.Movie
import com.example.gb_3_weather.R
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.marvel_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServieces
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MarvelMovieAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marvel_activity)

        mService = Common.retrofitService
        recyclerMovieList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerMovieList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllMovieList()
    }

    private fun getAllMovieList() {
        dialog.show()
        mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
                adapter = MarvelMovieAdapter(baseContext, response.body() as MutableList<Movie>)
                adapter.notifyDataSetChanged()
                recyclerMovieList.adapter = adapter

                dialog.dismiss()
            }
        })
    }
}