package com.uastravel.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "my_list")
data class UserEntityDetails(
    @PrimaryKey(autoGenerate = true) val orderId: Long = 0,
    @ColumnInfo(name = "placeName") var placeName: String,
    @ColumnInfo(name = "countryName") var countryName: String,
    @ColumnInfo(name = "price") var price: String,
    @ColumnInfo(name = "totalPrice") var totalPrice: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "imageUrl") var imageUrl: Int,
    @ColumnInfo(name = "aboutPlace") var aboutPlace: String,
    // Tambahkan properti lain sesuai kebutuhan.
): Serializable