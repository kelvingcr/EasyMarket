package com.kelvingcr.easymarket

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.kelvingcr.easymarket.databinding.ActivityMainBinding
import com.kelvingcr.easymarket.ui.fragments.auth.viewmodel.AuthFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<AuthFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setupTheme()
        setupOnClickListener()

    }

    private fun setupOnClickListener() {
        binding.fabNewPurchase.setOnClickListener {
            navigateSingleTop(R.id.action_global_newPurchaseFragment)

        }

        binding.BottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHomeFragment -> {
                    navigateSingleTop(R.id.action_global_homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menuSearchFragment -> {
                    navigateSingleTop(R.id.action_global_searchFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menuProfilePreference -> {
                    navigateSingleTop(R.id.action_global_preferenceScreen)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun setupTheme() {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(this)
        if (preferenceManager.getBoolean("onModeNight", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btnExit) {
           viewModel.disconnectUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateSingleTop(destination: Int) {

        val navController = findNavController(R.id.fragmentContainerView)
        val navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()

        navController.navigate(destination, null, navOptions)
    }


    override fun onResume() {
        viewModel.disconnectUserLiveData.observe(this, Observer {isDisconected ->
            if(isDisconected) {
                finish()
                startActivity(Intent(this, AuthActivity::class.java))
            }
        })
        super.onResume()
    }
}
