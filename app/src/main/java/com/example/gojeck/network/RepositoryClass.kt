package com.example.gojeck.network

import androidx.lifecycle.MutableLiveData
import com.example.gojeck.model.TrendingRepositoryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryClass() {

    private var urlInterface: URLInterface =
        RetrofitInstance.getClient()?.create(URLInterface::class.java)!!


    companion object {
        private object SingletonHelper {
            internal val INSTANCE = RepositoryClass()
        }

        fun getInstance(): RepositoryClass {
            return SingletonHelper.INSTANCE
        }
    }

    fun getRepository(): MutableLiveData<List<TrendingRepositoryModel>> {
        val data: MutableLiveData<List<TrendingRepositoryModel>> = MutableLiveData()
        urlInterface.gerRepository()
            .enqueue(object : Callback<List<TrendingRepositoryModel>> {
                override fun onResponse(
                    call: Call<List<TrendingRepositoryModel>>,
                    response: Response<List<TrendingRepositoryModel>>
                ) {
                    if (response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(
                    call: Call<List<TrendingRepositoryModel>>,
                    t: Throwable
                ) {
                    data.postValue(null)
                }
            })

        return data
    }
}


