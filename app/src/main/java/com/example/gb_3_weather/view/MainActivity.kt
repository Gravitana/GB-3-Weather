package com.example.gb_3_weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gb_3_weather.R
import com.example.gb_3_weather.databinding.MainActivityBinding
import com.example.gb_3_weather.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}