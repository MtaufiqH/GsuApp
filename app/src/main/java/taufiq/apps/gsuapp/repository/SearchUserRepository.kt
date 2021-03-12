package taufiq.apps.gsuapp.repository

import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse
import taufiq.apps.gsuapp.helper.Resource

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
interface SearchUserRepository {
    suspend fun getSearchUser(query: String) : Resource<SearchUserResponse>

}