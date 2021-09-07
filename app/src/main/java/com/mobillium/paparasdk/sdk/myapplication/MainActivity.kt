package com.mobillium.paparasdk.sdk.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(this).build())
            .build()

        val myOkHttpStack = OkHttpStack(client)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this, myOkHttpStack)
        val url = "https://www.google.com"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                "Response is: ${response.substring(0, 500)}".also { textView.text = it }
            },
            { "That didn't work!".also { textView.text = it } })

        stringRequest.tag = "TAG"

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}