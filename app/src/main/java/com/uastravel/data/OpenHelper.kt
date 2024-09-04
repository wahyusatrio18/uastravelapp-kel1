package com.uastravel.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uastravel.data.entity.DatabaseOptions.Companion.createTableUser
import com.uastravel.data.entity.DatabaseOptions.Companion.databaseName
import com.uastravel.data.entity.DatabaseOptions.Companion.databaseVersion
import com.uastravel.data.entity.DatabaseOptions.Companion.emailUser
import com.uastravel.data.entity.DatabaseOptions.Companion.idUser
import com.uastravel.data.entity.DatabaseOptions.Companion.nameUser
import com.uastravel.data.entity.DatabaseOptions.Companion.passwordUser
import com.uastravel.data.entity.DatabaseOptions.Companion.tbUser
import com.uastravel.data.entity.User

class OpenHelper(context : Context) : SQLiteOpenHelper(context, databaseName, null, databaseVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tbUser")
        onCreate(db)
    }

    // CRUD
    fun registerUser(user : User) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(emailUser, user.email)
        values.put(nameUser, user.name)
        values.put(passwordUser, user.password)
        db.insert(tbUser, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun verificLogin(email: String, password: String): Long {
        val data = arrayOf(email, password)

        val db = this.readableDatabase
        val query = "SELECT $idUser FROM $tbUser WHERE $emailUser LIKE? AND $passwordUser LIKE?"
        val cursor = db.rawQuery(query, data)

        return if (cursor.moveToFirst()) {
            val userId = cursor.getLong(cursor.getColumnIndex(idUser))
            cursor.close()
            userId
        } else {
            cursor.close()
            -1
        }
    }

}