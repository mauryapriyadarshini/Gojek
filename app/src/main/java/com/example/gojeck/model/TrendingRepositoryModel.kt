package com.example.gojeck.model

data class TrendingRepositoryModel(
    val author: String? = null,
    val name: String? = null,
    val avatar: String? = null,
    val url: String? = null,
    val description: String? = null,
    val language: String? = null,
    val languageColor: String? = null,
    val stars: Int? = 0,
    val forks: Int? = 0,
    val currentPeroidStars: Int? = 0,
    var isExpanded: Boolean = false
)