package ru.clonsaldafon.shoppinglistapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<UserEntity>?

    @Query("DELETE FROM user")
    suspend fun clear()

}