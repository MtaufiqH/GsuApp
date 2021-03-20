package taufiq.apps.consumerapp

import android.content.ContentResolver
import taufiq.apps.consumerapp.DatabaseContract.FavoriteUserColumn.Companion.MY_CONTENT_URI

/**
 * Created By Taufiq on 3/20/2021.
 *
 */
class UserDataSource(private val contentResolver: ContentResolver) {
    fun getUsers(): List<FavoriteUser> {
        val result: MutableList<FavoriteUser> = mutableListOf()

        val cursor = contentResolver.query(
            MY_CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.apply {
            while (moveToNext()) {
                result.add(
                    FavoriteUser(
                        getInt(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumn.ID)),
                        getString(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumn.USERNAME)),
                        getString(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumn.AVATAR_URL)),
                        getString(getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumn.NAME)),
                    )
                )
            }
            close()
        }
        return result.toList()
    }
}