package taufiq.apps.gsuapp.repository

import retrofit2.Response
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
interface SearchUserRepository {
    suspend fun getSearchUser(query: String): Response<SearchUserResponse>
}