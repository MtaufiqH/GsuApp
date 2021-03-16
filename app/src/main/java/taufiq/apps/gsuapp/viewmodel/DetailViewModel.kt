package taufiq.apps.gsuapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import taufiq.apps.gsuapp.data.remote.responses.detail.DetailResponse
import taufiq.apps.gsuapp.repository.SearchGithubRepo
import javax.inject.Inject

/**
 * Created By Taufiq on 3/16/2021.
 *
 */
@HiltViewModel
class DetailViewModel @Inject constructor(private val api: SearchGithubRepo) : ViewModel() {

    private val _dataDetail = MutableLiveData<DetailResponse>()
    val dataDetail: LiveData<DetailResponse> = _dataDetail

    fun getDataDetail(username: String) = viewModelScope.launch {
        api.getDetailUser(username).also {
            if (it.isSuccessful) {
                _dataDetail.value = it.body()
            } else {
                Log.d("DetailViewModel", "getDataDetail: ${it.message()} ${it.code()} ")
            }
        }
    }
}