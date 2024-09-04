package com.uastravel

import android.content.Context

class SessionManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    companion object {
        private lateinit var instance: SessionManager

        fun initialize(context: Context) {
            instance = SessionManager(context)
        }

        fun getInstance(): SessionManager {
            if (!::instance.isInitialized) {
                throw UninitializedPropertyAccessException("SessionManager has not been initialized")
            }
            return instance
        }
    }

    fun setUserId(userId: Long) {
        sharedPreferences.edit().putLong("userId", userId).apply()
    }

    fun getUserId(): Long {
        return sharedPreferences.getLong("userId", -1) // -1 sebagai nilai default jika tidak ada userId yang tersimpan
    }

    fun isLoggedIn(): Boolean {
        // Anggaplah pengguna dianggap login jika userId lebih besar dari 0
        return getUserId() > 0
    }

    fun logout() {
        // Hapus userId saat logout
        sharedPreferences.edit().remove("userId").apply()
    }
}

fun getUserIdFromSession(context: Context): Long {
    val sessionManager = SessionManager(context)
    return sessionManager.getUserId()
}
