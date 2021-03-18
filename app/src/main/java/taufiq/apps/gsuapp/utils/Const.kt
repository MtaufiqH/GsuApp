package taufiq.apps.gsuapp.utils

import taufiq.apps.gsuapp.BuildConfig

/**
 * Created By Taufiq on 3/16/2021.
 *
 */
object Const {
    const val BASE_URL = "https://api.github.com/"
    const val API_KEY = BuildConfig.API_KEY
    const val ARG_OBJECT = "object"
    const val DB_NAME = "Favorite_Database"

//    val MIGRATION_1_2 = object  :Migration(1,2){
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL(
//                "DROP TABLE  ;"
//            )
//        }
//
//    }
}