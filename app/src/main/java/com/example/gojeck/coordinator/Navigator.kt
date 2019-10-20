package com.example.gojeck.coordinator

import androidx.fragment.app.FragmentActivity
import com.example.gojeck.R
import com.example.gojeck.ui.TrendingRepositoryFragment
import com.example.gojeck.ui.WelcomeFragment

class Navigator {

    var activity: FragmentActivity? = null

    fun start() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, TrendingRepositoryFragment()).addToBackStack("Welcome")
            .commit()
    }

    fun welcomeScreen() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, WelcomeFragment()).commit()
    }

    fun trendingRepositoryScreen() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, TrendingRepositoryFragment()).commit()
    }
}