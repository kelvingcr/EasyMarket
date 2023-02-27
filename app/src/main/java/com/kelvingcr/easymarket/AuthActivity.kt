package com.kelvingcr.easymarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.kelvingcr.easymarket.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAuthBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onBackPressed(true) {
            if(findNavController(R.id.fragmentContainerView2).previousBackStackEntry != null) {
                findNavController(R.id.fragmentContainerView2).popBackStack()
                } else {
                    finish()
            }

        }
    }

    private fun AppCompatActivity.onBackPressed(isEnabled: Boolean, callback: () -> Unit) {
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(isEnabled) {
                override fun handleOnBackPressed() {
                    callback()
                }
            })
    }
}