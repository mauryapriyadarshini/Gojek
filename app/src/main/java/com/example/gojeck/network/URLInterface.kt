package com.example.gojeck.network

import androidx.lifecycle.MutableLiveData
import com.example.gojeck.model.TrendingRepositoryModel
import retrofit2.Call
import retrofit2.http.GET

interface URLInterface {
    @GET("/repositories")
    fun gerRepository(): Call<MutableLiveData<List<TrendingRepositoryModel>>>
}