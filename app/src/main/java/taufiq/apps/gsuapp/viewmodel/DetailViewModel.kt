package taufiq.apps.gsuapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import taufiq.apps.gsuapp.data.local.FavoriteUser
import taufiq.apps.gsuapp.data.remote.responses.detail.DetailResponse
import taufiq.apps.gsuapp.data.remote.responses.follower.FollowersResponses
import taufiq.apps.gsuapp.data.remote.responses.following.FollowingResponses
import taufiq.apps.gsuapp.repository.SearchGithubRepo
import taufiq.apps.gsuapp.repository.UserFavoriteRepository
import javax.inject.Inject

/**
 * Created By Taufiq on 3/16/2021.
 *
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val api: SearchGithubRepo,
    private val favRepo: UserFavoriteRepository
) : ViewModel() {

    private val _dataDetail = MutableLiveData<DetailResponse>()
    val dataDetail: LiveData<DetailResponse> = _dataDetail
    private val _dataFollowers = MutableLiveData<FollowersResponses>()
    val dataFollowers: LiveData<FollowersResponses> = _dataFollowers
    private val _dataFollowing = MutableLiveData<FollowingResponses>()
    val dataFollowing: LiveData<FollowingResponses> = _dataFollowing

    val isFavorit = MutableLiveData(false)

    fun getDataDetail(username: String) = viewModelScope.launch {
        api.getDetailUser(username).also {
            if (it.isSuccessful) {
                _dataDetail.value = it.body()
            } else {
                Log.d("DetailViewModel", "getDataDetail: ${it.message()} ${it.code()} ")
            }
        }
    }

    fun getFollowers(userName: String) = viewModelScope.launch {
        api.getFollower(userName).also {
            if (it.isSuccessful) {
                _dataFollowers.value = it.body()
            } else
                Log.d("DetailViewModel", "getDataFollowers: ${it.message()} ${it.code()} ")
        }
    }


    fun getFollowing(userName: String) = viewModelScope.launch {
        api.getFollowing(userName).also {
            if (it.isSuccessful) {
                _dataFollowing.value = it.body()
            } else
                Log.d("DetailViewModel", "getDataFollowing: ${it.message()} ${it.code()} ")
        }
    }

    fun insertToFavorite(user: FavoriteUser) = viewModelScope.launch {
        favRepo.insertUserToFavorite(user)
    }

    fun deleteFromFavorite(user: FavoriteUser) = viewModelScope.launch {
        favRepo.deleteUserFavorite(user)
    }

     fun checkUserFavorite(id: Int) = favRepo.checkUserFavorite(id)

}