package com.example.gojeck.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.gojeck.coordinator.Navigator
import com.example.gojeck.coordinator.TrendingRepositoryCoordinator
import com.example.gojeck.coordinator.WelcomeCoordinator
import com.example.gojeck.R


class GojeckBaseActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    lateinit var welcomeCoordinator: WelcomeCoordinator
    lateinit var trendingRepositoryCoordinator: TrendingRepositoryCoordinator
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gojeck_base_activity)
        initializeToolbar()
        initializeCoordinator()
    }

    private fun initializeCoordinator() {
        navigator.activity = this

        welcomeCoordinator = WelcomeCoordinator(navigator)
        trendingRepositoryCoordinator = TrendingRepositoryCoordinator(navigator)
        welcomeCoordinator.startWelcomeCoordinator()
    }

    // <---------- ToolBar function ------>

    private fun initializeToolbar() {
        toolbar = findViewById(R.id.trToolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
