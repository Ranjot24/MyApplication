package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrderApiClient {

    companion object {
        private const val BASE_URL = "https://your-api-base-url.com/"

        fun create(): OrderService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(OrderService::class.java)
        }
    }
}

