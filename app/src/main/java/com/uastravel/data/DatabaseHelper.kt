package com.uastravel.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uastravel.data.entity.MyOrder

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "MyOrder.db"
        const val DATABASE_VERSION = 2

        const val TABLE_NAME = "orders"
        const val COLUMN_ID = "id"
        const val COLUMN_USER_ID = "user_id"  // Tambahkan kolom user_id
        const val COLUMN_PLACE_NAME = "place_name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_DEPARTURE_DATE = "departure_date"

        private const val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_USER_ID INTEGER, " +  // Ubah tipe kolom user_id
                    "$COLUMN_PLACE_NAME TEXT, $COLUMN_PRICE REAL, $COLUMN_DEPARTURE_DATE TEXT);"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Tambahkan parameter userId ke metode insertOrder
    fun insertOrder(userId: Long, placeName: String, price: String, departureDate: String) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_USER_ID, userId)
        contentValues.put(COLUMN_PLACE_NAME, placeName)
        contentValues.put(COLUMN_PRICE, price)
        contentValues.put(COLUMN_DEPARTURE_DATE, departureDate)
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }

    // Tambahkan parameter userId ke metode getAllOrders
    @SuppressLint("Range")
    fun getAllOrders(userId: Long): List<MyOrder> {
        val orders = mutableListOf<MyOrder>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_USER_ID = ?", arrayOf(userId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val placeName = cursor.getString(cursor.getColumnIndex(COLUMN_PLACE_NAME))
                val price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE))
                val departureDate = cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTURE_DATE))
                val order = MyOrder(id, placeName, price, departureDate)
                orders.add(order)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return orders
    }

    // Tambahkan parameter userId ke metode deleteOrder
    fun deleteOrder(userId: Long, orderId: Long) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ? AND $COLUMN_USER_ID = ?"
        val whereArgs = arrayOf(orderId.toString(), userId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}