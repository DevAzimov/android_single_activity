package com.magicapp.android_saas.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.magicapp.android_saas.R
import com.magicapp.android_saas.databinding.ActivityMainBinding
import com.magicapp.android_saas.managers.AuthManager

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val SPLASH_SCREEN = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        Handler().postDelayed({
            when {
                AuthManager.isAuthorized -> {
                    navGraph.setStartDestination(R.id.mainFlowFragment)
                }
                !AuthManager.isAuthorized -> {
                    navGraph.setStartDestination(R.id.authFlowFragment)
                }
            }
            navController.graph = navGraph
        }, SPLASH_SCREEN.toLong())
    }
}