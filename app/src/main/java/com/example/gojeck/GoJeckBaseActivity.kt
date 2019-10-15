package com.example.gojeck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GoJeckBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gojeck_base_activity)
    }
}
