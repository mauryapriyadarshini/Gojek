package com.example.gojeck.network

import com.example.gojeck.BaseApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(BaseApplication.getInstance().applicationContext.cacheDir, cacheSize)
        private var retrofit: Retrofit? = null
        private const val BASE_URL: String = "https://github-trending-api.now.sh"
        private var loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private var clientBuilder =
            OkHttpClient.Builder().cache(myCache).addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (NetworkUtils.haveNetworkConnection())
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                    else
                        request.newBuilder().header(
                            "Cache-Control",
                            "public, only-if-cached, max-stale=" + 60 * 60 * 2
                        ).build()
                    chain.proceed(request)
                }.connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build()


        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .client(clientBuilder)
                    .build()
            }
            return retrofit
        }
    }
}