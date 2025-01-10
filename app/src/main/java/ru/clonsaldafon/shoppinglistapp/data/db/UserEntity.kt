package ru.clonsaldafon.shoppinglistapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.clonsaldafon.shoppinglistapp.data.db.UserEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accessToken: String?,
    val refreshToken: String?,
) {

    companion object {
        const val TABLE_NAME = "user"
    }

}
