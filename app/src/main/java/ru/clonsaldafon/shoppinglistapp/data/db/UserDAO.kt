package ru.clonsaldafon.shoppinglistapp.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDAO {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<UserEntity>?

    @Query("DELETE FROM user")
    suspend fun clear()

}