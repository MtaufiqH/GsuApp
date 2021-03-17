package taufiq.apps.gsuapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getUserFavoriteDao(): FavoriteDao
}