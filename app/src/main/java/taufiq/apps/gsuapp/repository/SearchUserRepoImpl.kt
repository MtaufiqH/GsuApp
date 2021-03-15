package taufiq.apps.gsuapp.repository

import retrofit2.Response
import taufiq.apps.gsuapp.data.remote.client.GithubClient
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse
import javax.inject.Inject

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class SearchUserRepoImpl @Inject constructor(
    private val apiService: GithubClient
) {
    suspend fun getSearchUser(query: String): Response<SearchUserResponse> =
        apiService.getSearchUser(query)

}