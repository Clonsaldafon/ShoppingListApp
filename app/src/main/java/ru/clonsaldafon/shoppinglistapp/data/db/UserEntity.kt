package ru.clonsaldafon.shoppinglistapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.clonsaldafon.shoppinglistapp.data.db.UserEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val login: String?,
    val password: String?,
    val accessToken: String?,
    val refreshToken: String?
) {

    companion object {
        const val TABLE_NAME = "user"
    }

}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN login TEXT")
        db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN password TEXT")
    }
}
