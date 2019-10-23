package com.example.gojeck.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.gojeck.model.TrendingRepositoryModel
import com.example.gojeck.network.RepositoryClass

class RepositoryViewModel(application: Application) : AndroidViewModel(application) {
    var repositoryList: MutableLiveData<List<TrendingRepositoryModel>> =
        MutableLiveData<List<TrendingRepositoryModel>>()

    fun getRepository(): MutableLiveData<List<TrendingRepositoryModel>> {
        repositoryList = RepositoryClass.getInstance().getRepository()
        return repositoryList
    }
}
