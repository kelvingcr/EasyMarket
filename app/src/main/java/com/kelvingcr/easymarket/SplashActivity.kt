package com.kelvingcr.easymarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.kelvingcr.easymarket.databinding.ActivitySplashBinding
import com.kelvingcr.easymarket.ui.fragments.auth.viewmodel.AuthFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<AuthFragmentViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.currentUserLiveData.observe(this, Observer { user ->
            if(user != null) {
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                finish()
                startActivity(Intent(this, AuthActivity::class.java))
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getCurrentUser()
        }, 3000)
    }
}