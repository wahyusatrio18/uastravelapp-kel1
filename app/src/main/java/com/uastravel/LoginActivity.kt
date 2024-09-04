package com.uastravel

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.uastravel.data.OpenHelper
import com.uastravel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun login(view: View) {
        val preferences = SharedPreferences.getInstance(this)
        val database = OpenHelper(this)

        val email = binding.edtEmailLogin.text.trim().toString()
        val password = binding.edtPasswordLogin.text.trim().toString()

        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.toastRegister), Toast.LENGTH_SHORT).show()

        } else {
            var userId = database.verificLogin(email, password)

            if(userId != -1L) {
                preferences.addString(getString(R.string.email), email)
                preferences.addBoolean(getString(R.string.logged), true)
                val sessionManager = SessionManager(this)
                sessionManager.setUserId(userId)
                Log.d("LoginActivity", "UserId: $userId")
                Toast.makeText(this, getString(R.string.sucessLogin), Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, getString(R.string.failedLogin), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun register(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

}
