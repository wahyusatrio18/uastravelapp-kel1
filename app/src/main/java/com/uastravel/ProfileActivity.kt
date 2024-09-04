package com.uastravel

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.uastravel.data.OpenHelper
import com.uastravel.SharedPreferences
import com.uastravel.data.entity.DatabaseOptions

class ProfileActivity : AppCompatActivity() {

    private lateinit var dbHelper: OpenHelper
    private lateinit var preferences: SharedPreferences
    private lateinit var loggedInEmail: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbHelper = OpenHelper(this)
        preferences = SharedPreferences.getInstance(this)
        loggedInEmail = preferences.getString(getString(R.string.email)) ?: ""

        displayUserProfile()
        val editProfileButton = findViewById<Button>(R.id.usr_edit_details)
        editProfileButton.setOnClickListener {
            editProfile()
        }
    }
    companion object {
        const val EDIT_PROFILE_REQUEST = 1 // Anda dapat menggunakan nilai lain sesuai kebutuhan
    }

    private fun displayUserProfile() {
        // Ambil username pengguna yang sedang login dari SharedPreferences
        val loggedInEmail = preferences.getString(getString(R.string.email)) ?: ""

        // Ambil data pengguna dari SQLite
        val db = dbHelper.readableDatabase
        val projection = arrayOf(DatabaseOptions.nameUser, DatabaseOptions.emailUser)

        val selection = "${DatabaseOptions.emailUser} = ?"
        val selectionArgs = arrayOf(loggedInEmail)


        val cursor = db.query(DatabaseOptions.tbUser, projection, selection, selectionArgs, null, null, null)

        if (cursor.moveToFirst()) {
            val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseOptions.nameUser))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseOptions.emailUser))

            // Tampilkan data pengguna di antarmuka pengguna (UI)
            findViewById<TextView>(R.id.textViewName).text = username
            findViewById<TextView>(R.id.textViewEmail).text = email

            // Pernyataan log untuk memeriksa nilai yang diambil
            Log.d("ProfileActivity", "Username: $username, Email: $email")
        } else {
            // Pernyataan log jika tidak ada data yang ditemukan
            Log.d("ProfileActivity", "No data found for username: $loggedInEmail")
        }

        cursor.close()
    }

    private fun editProfile() {
        val intent = Intent(this, EditProfileActivity::class.java)
        intent.putExtra("emailUser", loggedInEmail)
        startActivityForResult(intent, EDIT_PROFILE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_PROFILE_REQUEST && resultCode == Activity.RESULT_OK) {
            val newName = data?.getStringExtra("newName")
            val newEmail = data?.getStringExtra("newEmail")

            if (!newName.isNullOrBlank() && !newEmail.isNullOrBlank()) {
                updateProfileInDatabase(loggedInEmail, newName, newEmail)
                refreshProfileView()
                logout(findViewById<Button>(R.id.btnLogout))

            }
        }
    }


    fun updateProfileInDatabase(loggedInEmail: String, newName: String, newEmail: String) {
        // Update data pengguna ke database
        val db = dbHelper.writableDatabase

        try {
            // Mulai transaksi
            db.beginTransaction()

            // Proses pembaruan database di sini
            val values = ContentValues().apply {
                put(DatabaseOptions.nameUser, newName)
                put(DatabaseOptions.emailUser, newEmail)
            }

            val selection = "${DatabaseOptions.emailUser} = ?"
            val selectionArgs = arrayOf(loggedInEmail)

            db.update(DatabaseOptions.tbUser, values, selection, selectionArgs)

            // Set transaksi berhasil jika sampai di sini tanpa exception
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            // Tangani exception, misalnya log atau tampilkan pesan kesalahan
            Log.e("ProfileActivity", "Error updating profile in database", e)
        } finally {
            // Akhiri transaksi
            db.endTransaction()
        }
    }

    fun refreshProfileView() {
        displayUserProfile() // Memanggil fungsi untuk menampilkan ulang data
    }

    fun logout(view: View) {
        // Menghapus data login dari SharedPreferences
        preferences.removeKey(getString(R.string.email))
        preferences.removeKey(getString(R.string.logged))

        Toast.makeText(this, getString(R.string.logout), Toast.LENGTH_SHORT).show()

        // Navigasi ke LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
