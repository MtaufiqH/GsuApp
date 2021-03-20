package taufiq.apps.gsuapp.utils.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import taufiq.apps.gsuapp.data.local.FavoriteDatabase
import taufiq.apps.gsuapp.data.local.FavoriteUserDao
import javax.inject.Inject

class UserFavoriteProviders : ContentProvider() {
    private lateinit var userDao: FavoriteUserDao

    companion object {
        private const val AUTHORITY_URI = "taufiq.apps.gsuapp"
        private const val TABLE_NAME = "favorite_table"
        const val ID_FAVORITE_USER = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY_URI, TABLE_NAME, ID_FAVORITE_USER)
        }
    }


    @Inject
    private lateinit var db: FavoriteDatabase

    override fun onCreate(): Boolean {
        userDao = db.getUserFavoriteDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when (uriMatcher.match(uri)) {
            ID_FAVORITE_USER -> {
                cursor = userDao.findAll()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                cursor = null
            }
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0
}