package com.example.gb_3_weather.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.gb_3_weather.R
import com.example.gb_3_weather.databinding.MainActivityWebviewBinding
import com.example.gb_3_weather.view.main.MainFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener(clickListener)

/*
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
*/
    }

    var clickListener: View.OnClickListener = object : View.OnClickListener {

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onClick(v: View?) {
            try {
                val uri = URL(binding.url.text.toString())
                val handler = Handler() //Запоминаем основной поток
                Thread {
                    var urlConnection: HttpsURLConnection? = null
                    try {
                        urlConnection = uri.openConnection() as HttpsURLConnection
                        urlConnection.requestMethod = "GET" //установка метода получения данных — GET
                        urlConnection.readTimeout = 10000 //установка таймаута — 10 000 миллисекунд
                        val reader =
                                BufferedReader(InputStreamReader(urlConnection.inputStream)) //читаем данные в поток
                        val result = getLines(reader)

                        // Возвращаемся к основному потоку
                        handler.post {
                            //binding.webview.loadData(result, "text/html; charset=utf-8", "utf-8")
                            // На некоторых новых версиях операционной системы контент WebView может не отображаться из-за определенной версии Chrome.
                            // Используйте метод loadDataWithBaseURL вместо loadData:
                            binding.webview.loadDataWithBaseURL(null, result, "text/html; charset=utf-8", "utf-8", null)
                        }
                    } catch (e: Exception) {
                        Log.e("", "Fail connection", e)
                        e.printStackTrace()
                    } finally {
                        urlConnection?.disconnect()
                    }
                }.start()
            } catch (e: MalformedURLException) {
                Log.e("", "Fail URI", e)
                e.printStackTrace()
            }
        }

        @RequiresApi(Build.VERSION_CODES.N)
        private fun getLines(reader: BufferedReader): String {
            return reader.lines().collect(Collectors.joining("\n"))
        }
    }
}