package taufiq.apps.consumerapp

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class UserFavoriteRepository @Inject constructor(private val data: UserDataSource) {

    val allFavoriteUser = liveData {
        withContext(Dispatchers.IO) {
            emit(data.getUsers())
        }

    }
}