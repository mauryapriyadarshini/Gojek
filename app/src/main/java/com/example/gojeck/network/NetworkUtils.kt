package com.example.gojeck.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.gojeck.BaseApplication

class NetworkUtils {
    companion object {
        fun haveNetworkConnection(): Boolean {
            var haveConnectedWifi = false
            var haveConnectedMobile = false
            val cm =
                BaseApplication.getInstance().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.allNetworkInfo
            for (ni in netInfo) {
                if (ni.typeName.equals("WIFI", ignoreCase = true))
                    if (ni.isConnected)
                        haveConnectedWifi = true
                if (ni.typeName.equals("MOBILE", ignoreCase = true))
                    if (ni.isConnected)
                        haveConnectedMobile = true
            }
            return haveConnectedWifi || haveConnectedMobile
        }
    }
}

