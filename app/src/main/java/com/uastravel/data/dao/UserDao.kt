package com.uastravel.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.uastravel.data.entity.DatabaseOptions
import com.uastravel.data.entity.User
import com.uastravel.data.entity.UserEntityDetails

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntityDetails)

    @Update
    fun update(tbUser: DatabaseOptions)

    @Insert
    fun insertAll(vararg tbUser: DatabaseOptions)
}