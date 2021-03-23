package taufiq.apps.consumerapp

import android.net.Uri
import android.provider.BaseColumns

/**
 * Created By Taufiq on 3/20/2021.
 *
 */
object DatabaseContract {
    private const val AUTHORITY = "taufiq.apps.gsuapp"
    private const val SCHEME = "content"

    class FavoriteUserColumn : BaseColumns {
        companion object {
            private const val TABLE_NAME = "favorite_table"
            const val ID = "id"
            const val USERNAME = "username"
            const val AVATAR_URL = "avatar_url"

            val MY_CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}