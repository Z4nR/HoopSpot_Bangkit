package com.zulham.hoopspot.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.zulham.hoopspot.R
import com.zulham.hoopspot.databinding.ActivitySplashBinding
import com.zulham.hoopspot.ui.place.PlaceActivity
import kotlinx.coroutines.InternalCoroutinesApi

class SplashActivity : AppCompatActivity() {

    private lateinit var bindingSplash: ActivitySplashBinding

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        bindingSplash = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bindingSplash.root)

        val splashScreenTimeOut = 3000
        val homeIntent = Intent(this, PlaceActivity::class.java)

        Handler(mainLooper).postDelayed({
            startActivity(homeIntent)
            finish()
        }, splashScreenTimeOut.toLong())

    }
}