package taufiq.apps.gsuapp.repository

import taufiq.apps.gsuapp.data.local.FavoriteDao
import taufiq.apps.gsuapp.data.local.FavoriteEntity
import javax.inject.Inject

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class UserFavoriteRepository @Inject constructor(private val dao: FavoriteDao) {

    val allFavoriteUser = dao.getAllFavoriteUser()

    fun getFavUserByUserName(username: String) =
        dao.getFavoriteUser(username)

    suspend fun insertUserToFavorite(user: FavoriteEntity) =
        dao.insertUserToFavorite(user)

    suspend fun deleteUserFavorite(user: FavoriteEntity) =
        dao.deleteUserFromFavorite(user)

}