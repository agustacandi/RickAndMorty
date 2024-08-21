package com.takehomechallenge.candi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.takehomechallenge.candi.R
import com.takehomechallenge.candi.databinding.ActivityMainBinding
import com.takehomechallenge.core.utils.ext.gone
import com.takehomechallenge.core.utils.ext.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigationDestination =
        listOf(R.id.homeFragment, R.id.searchFragment, R.id.favoriteFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navHostBottomBar =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navControllerBottomBar = navHostBottomBar.navController

        with(binding) {
            mainBottomNavigation.setupWithNavController(navControllerBottomBar)
            navControllerBottomBar.addOnDestinationChangedListener { _, destination, _ ->
                if (navigationDestination.contains(destination.id)) {
                    mainBottomNavigation.show()
                } else {
                    mainBottomNavigation.gone()
                }
            }
        }
    }
}