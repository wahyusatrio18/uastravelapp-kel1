package com.uastravel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditProfileActivity : AppCompatActivity() {
    private lateinit var btnSave: Button
    private lateinit var edtNewName: EditText
    private lateinit var edtNewEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Inisialisasi elemen UI
        btnSave = findViewById(R.id.btnSave)
        edtNewName = findViewById(R.id.editNama)
        edtNewEmail = findViewById(R.id.editEmail)

        val loggedInEmail = intent.getStringExtra("emailUser")
        // Menggunakan data ini untuk menampilkan di UI atau memasukkannya ke EditText

        btnSave.setOnClickListener {
            val newName = edtNewName.text.toString()
            val newEmail = edtNewEmail.text.toString()

            if (isValidInput(newName, newEmail)) {
                // Memberikan tahu bahwa data telah diubah
                val resultIntent = Intent()
                resultIntent.putExtra("newName", newName)
                resultIntent.putExtra("newEmail", newEmail)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidInput(name: String, email: String): Boolean {
        // Tambahkan logika validasi Anda di sini
        return name.isNotBlank() && email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

