package taufiq.apps.gsuapp.repository

import taufiq.apps.gsuapp.data.remote.client.GithubClient
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse
import taufiq.apps.gsuapp.helper.Resource
import javax.inject.Inject

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class SearchUserRepoImpl @Inject constructor(
    private val api: GithubClient
) : SearchUserRepository {

    override suspend fun getSearchUser(query: String): Resource<SearchUserResponse> {
        return try {
            val response = api.getSearchUser(query)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else
                Resource.Error(response.message())
        } catch (e: Exception) {
            Resource.Error(e.message.toString() ?: "an error occurred")
        }
    }
}