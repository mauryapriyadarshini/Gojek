package com.example.gojeck.network

import androidx.lifecycle.MutableLiveData
import com.example.gojeck.model.TrendingRepositoryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryClass() {

    private var urlInterface: URLInterface

    companion object {
        private object SingletonHelper {
            internal val INSTANCE = RepositoryClass()
        }

        fun getInstance(): RepositoryClass {
            return Companion.SingletonHelper.INSTANCE
        }
    }

    init {
        urlInterface = RetrofitInstance.getClient()?.create(URLInterface::class.java)!!
    }

    fun getRepository(): MutableLiveData<List<TrendingRepositoryModel>> {
        var data: MutableLiveData<List<TrendingRepositoryModel>> =
            MutableLiveData<List<TrendingRepositoryModel>>()

        urlInterface.gerRepository()
            .enqueue(object : Callback<MutableLiveData<List<TrendingRepositoryModel>>> {
                override fun onResponse(
                    call: Call<MutableLiveData<List<TrendingRepositoryModel>>>,
                    response: Response<MutableLiveData<List<TrendingRepositoryModel>>>
                ) {

                    if (response.isSuccessful) {
                        data = response.body()!!
                    }
                }

                override fun onFailure(
                    call: Call<MutableLiveData<List<TrendingRepositoryModel>>>,
                    t: Throwable
                ) {
                    //TODO implement no network data
                }
            })

        return data
    }

}


