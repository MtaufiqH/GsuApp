package taufiq.apps.consumerapp

import androidx.lifecycle.liveData
import javax.inject.Inject

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class UserFavoriteRepository @Inject constructor(private val data: UserDataSource) {

    val allFavoriteUser = liveData {
        emit(data.getUsers())
    }
}