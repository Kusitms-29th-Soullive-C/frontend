package com.example.soullive

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.soullive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnv.setupWithNavController(navController)

        hideBottomNavigationView(navController)
    }

    private fun hideBottomNavigationView(navController: NavController) {
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            binding.bnv.visibility = when(destination.id){
                R.id.inputStartFragment -> View.GONE
                R.id.inputStep1Fragment -> View.GONE
                R.id.inputStep2Fragment -> View.GONE
                R.id.inputStep3Fragment -> View.GONE
                R.id.inputStep4Fragment -> View.GONE
                R.id.inputStep5Fragment -> View.GONE
                R.id.outputStep1Fragment -> View.GONE
                else -> View.VISIBLE
            }
        }
    }
}