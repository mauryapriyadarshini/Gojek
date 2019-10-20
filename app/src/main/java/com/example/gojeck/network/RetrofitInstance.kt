package com.example.gojeck.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL: String = "https://github-trending-api.now.sh"

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}