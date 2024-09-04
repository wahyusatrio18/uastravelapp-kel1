package com.uastravel

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uastravel.adapter.MyOrderAdapter
import com.uastravel.data.DatabaseHelper
import com.uastravel.data.entity.UserEntityDetails
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailsActivity : AppCompatActivity() {

    private lateinit var selectedData: Any
    private var selectedImageUri: Uri? = null
    private lateinit var imageViewForItem: ImageView
    private lateinit var textForNamePlace: TextView
    private lateinit var textForNameCountry: TextView
    private lateinit var textForTotalPrice: TextView
    private lateinit var textForAbout: TextView
    private lateinit var adapter: MyOrderAdapter
    private lateinit var databaseHelper: DatabaseHelper


    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    fun onBackButtonClick(view: View) {
        // Fungsi ini akan dipanggil ketika tombol back diklik
        finish() // Menutup aktivitas saat tombol diklik
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        imageViewForItem = findViewById(R.id.imageViewForItem)
        textForNamePlace = findViewById(R.id.textForNamePlace)
        textForNameCountry = findViewById(R.id.textForNameCountry)
        textForTotalPrice = findViewById(R.id.textForTotalPrice)
        textForAbout = findViewById(R.id.textForAbout)

        // Terima data dari intent dengan kunci yang sesuai
        selectedData = intent.getSerializableExtra("selectedData") ?: return
        // Isi tampilan dengan data tempat wisata yang diklik
        fillDetails(selectedData)

        // Setel listener untuk tombol booking
        databaseHelper = DatabaseHelper(this)

        val buttonForBooking: Button = findViewById(R.id.buttonForBooking)
        buttonForBooking.setOnClickListener {
            showBookingDialog()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            // Setel gambar di ImageView
            imageViewForItem.setImageURI(selectedImageUri)
        }
    }

    private fun fillDetails(selectedData: Any) {
        // Isi elemen tampilan dengan data tempat wisata yang diklik

        if (selectedData is UserEntityDetails) {
            imageViewForItem.setImageResource(selectedData.imageUrl)
            textForNamePlace.text = selectedData.placeName
            textForNameCountry.text = selectedData.countryName
            textForTotalPrice.text = selectedData.totalPrice.toString()
            textForAbout.text = selectedData.aboutPlace


        } else if (selectedData is UserEntityDetails) {
            imageViewForItem.setImageResource(selectedData.imageUrl)
            textForNamePlace.text = selectedData.placeName
            textForNameCountry.text = selectedData.countryName
            textForTotalPrice.text = selectedData.totalPrice.toString()
            textForAbout.text = selectedData.aboutPlace

        }
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    @SuppressLint("CutPasteId")
    private fun showBookingDialog() {
        val userId = getUserIdFromSession(applicationContext)
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_order_confirmation, null)
        dialogBuilder.setView(dialogView)

        val placeNameTextView: TextView = dialogView.findViewById(R.id.dialogTextForNamePlace)
        val priceTextView: TextView = dialogView.findViewById(R.id.dialogTextForTotalPrice)
        val dateButton: Button = dialogView.findViewById(R.id.btndate)
        val dateTextView: TextView = dialogView.findViewById(R.id.tvdate)

        // Set nilai placeName dan price sesuai dengan data yang ada
        placeNameTextView.text = textForNamePlace.text
        priceTextView.text = textForTotalPrice.text

        // Inisialisasi tanggal default di TextView
        dateTextView.text = "--/--/----"

        // Setel listener untuk tombol tanggal
        dateButton.setOnClickListener {
            showDatePickerDialog(dateTextView)
        }

        dialogBuilder.setTitle("Booking Information")
        dialogBuilder.setPositiveButton("Book") { _, _ ->
            val placeName = placeNameTextView.text.toString()
            val price = priceTextView.text.toString()
            val departureDate = dateTextView.text.toString()

            databaseHelper.insertOrder(userId, placeName, price, departureDate)

            Toast.makeText(this, "Booking success!", Toast.LENGTH_SHORT).show()
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun showDatePickerDialog(dateTextView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = formatDate(selectedYear, selectedMonth, selectedDay)
            dateTextView.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }
}

