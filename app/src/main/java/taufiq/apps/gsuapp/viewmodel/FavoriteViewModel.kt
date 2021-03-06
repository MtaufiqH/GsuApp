package taufiq.apps.gsuapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import taufiq.apps.gsuapp.data.local.FavoriteUser
import taufiq.apps.gsuapp.repository.UserFavoriteRepository
import javax.inject.Inject

/**
 * Created By Taufiq on 3/17/2021.
 *
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favRepo: UserFavoriteRepository
) : ViewModel() {

    val allFavoriteUser: LiveData<List<FavoriteUser>> = favRepo.allFavoriteUser.asLiveData()
}