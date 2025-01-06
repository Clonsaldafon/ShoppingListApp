package ru.clonsaldafon.shoppinglistapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.clonsaldafon.shoppinglistapp.data.model.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao: UserDAO

}