package taufiq.apps.gsuapp.data.local

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created By Taufiq on 3/18/2021.
 *
 */
@Dao
interface FavoriteUserDao {
    @Query("SELECT * FROM favorite_table")
    fun getAllFavoriteUser(): Flow<List<FavoriteUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserToFavorite(user: FavoriteUser)

    @Query("DELETE FROM favorite_table WHERE id= :id")
    suspend fun deleteUserFromFavorite(id: Int) : Int

    @Query("SELECT count(*) FROM favorite_table WHERE id = :id")
    fun checkUserFavs(id: Int): Int

    @Query("SELECT * FROM favorite_table")
    fun findAll(): Cursor

}