package com.uastravel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.util.Log


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val preferences = SharedPreferences.getInstance(this)
        val isLogged = preferences.getBoolean("logged")

        Log.d("SplashActivity", "Status Login: $isLogged")

        val run = Runnable {
            showActivity()
        }

        val duration: Long = 2000
        Handler().postDelayed(run, duration)
    }

    private fun showActivity() {
        val preferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE)
        val isLogged = preferences.getBoolean(getString(R.string.logged), false)

        val intent = when (isLogged) {
            true -> Intent(this, MainActivity::class.java)
            false -> Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}