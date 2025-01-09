package ru.clonsaldafon.shoppinglistapp.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

@Dao
interface UserDAO {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT accessToken, refreshToken FROM user")
    suspend fun getUser(): TokenResponse?

}