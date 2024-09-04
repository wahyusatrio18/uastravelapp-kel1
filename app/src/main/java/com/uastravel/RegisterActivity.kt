package com.uastravel

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uastravel.data.OpenHelper
import com.uastravel.data.entity.User
import com.uastravel.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    fun onBackButtonClick(view: View) {
        // Fungsi ini akan dipanggil ketika tombol back diklik
        onBackPressed() // Menutup aktivitas saat tombol diklik
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun doRegister(view: View) {
        val database = OpenHelper(this)

        val email = binding.edtEmailRegister.text.trim().toString()
        val name = binding.edtNameRegister.text.trim().toString()
        val password = binding.edtPasswordRegister.text.trim().toString()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.toastRegister), Toast.LENGTH_SHORT).show()
        } else {
            database.registerUser(User(email,name,password))
            Toast.makeText(this, getString(R.string.sucessRegister), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
