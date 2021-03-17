package taufiq.apps.gsuapp.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
@Parcelize
@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_name")
    val userName: String,
    val name: String,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    val bio: String?,
    val company: String?,
    val followers: Int?,
    val following: Int?,
    val location: String?,
    @ColumnInfo(name = "public_repos") val publicRepos: Int?,
    @ColumnInfo(name = "followers_url") val followersUrl: String?,
    @ColumnInfo(name = "following_url") val followingUrl: String?
) : Parcelable