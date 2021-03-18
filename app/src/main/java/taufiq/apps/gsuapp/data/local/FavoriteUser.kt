package taufiq.apps.gsuapp.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created By Taufiq on 3/18/2021.
 *
 */
@Parcelize
@Entity(tableName = "favorite_table")
data class FavoriteUser(
    @PrimaryKey
    val id: Int,
    val username: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    val name: String
) : Parcelable