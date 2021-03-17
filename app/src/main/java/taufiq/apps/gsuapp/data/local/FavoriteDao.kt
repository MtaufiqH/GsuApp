package taufiq.apps.gsuapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_table")
    suspend fun getAllFavoriteUser(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserToFavorite(user: FavoriteEntity)

    @Delete
    suspend fun deleteUserFromFavorite(user: FavoriteEntity)

    @Query("SELECT * FROM favorite_table WHERE user_name = :userName")
    suspend fun getFavoriteUser(userName: String): Flow<List<FavoriteEntity>>

}