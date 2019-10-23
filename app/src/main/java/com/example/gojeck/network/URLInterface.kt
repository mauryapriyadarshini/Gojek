package com.example.gojeck.network

import com.example.gojeck.model.TrendingRepositoryModel
import retrofit2.Call
import retrofit2.http.GET

interface URLInterface {
    @GET("/repositories")
    fun gerRepository(): Call<List<TrendingRepositoryModel>>
}