package taufiq.apps.consumerapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserFavoriteRepository

    init {
        val source = UserDataSource(application.contentResolver)
        repository = UserFavoriteRepository(source)
    }

    val allFavoriteUser: LiveData<List<FavoriteUser>> = repository.allFavoriteUser
}