package taufiq.apps.gsuapp.repository

import retrofit2.Response
import taufiq.apps.gsuapp.data.remote.client.GithubClient
import taufiq.apps.gsuapp.data.remote.responses.detail.DetailResponse
import taufiq.apps.gsuapp.data.remote.responses.follower.FollowersResponse
import taufiq.apps.gsuapp.data.remote.responses.following.FollowingResponse
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse
import javax.inject.Inject

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
class SearchGithubRepo @Inject constructor(
    private val apiService: GithubClient
) {
    suspend fun getSearchUser(query: String): Response<SearchUserResponse> =
        apiService.getSearchUser(query)

    suspend fun getDetailUser(username: String): Response<DetailResponse> =
        apiService.getDetailUser(username)

    suspend fun getFollower(username: String): Response<FollowersResponse> =
        apiService.getUserFollowers(username)

    suspend fun getFollowing(username: String): Response<FollowingResponse> =
        apiService.getUserFollowing(username)
}