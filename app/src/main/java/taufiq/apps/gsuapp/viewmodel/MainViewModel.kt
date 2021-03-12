package taufiq.apps.gsuapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse
import taufiq.apps.gsuapp.repository.SearchUserRepoImpl
import taufiq.apps.gsuapp.utils.Resource
import javax.inject.Inject

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: SearchUserRepoImpl
) : ViewModel() {

    private val _dataSearchUser = MutableLiveData<Resource<SearchUserResponse>>()
    val dataSearchUser: LiveData<Resource<SearchUserResponse>> = _dataSearchUser

    fun getSearchUser(query: String) = viewModelScope.launch {
        _dataSearchUser.value = Resource.loading(null)
        repo.getSearchUser(query).let {
            if (it.isSuccessful) {
                _dataSearchUser.postValue(Resource.success(it.body()))
            } else {
                _dataSearchUser.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }

    }


}
