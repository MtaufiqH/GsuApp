package taufiq.apps.gsuapp.data.remote.client

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import taufiq.apps.gsuapp.data.remote.responses.detail.DetailResponse
import taufiq.apps.gsuapp.data.remote.responses.follower.FollowersResponses
import taufiq.apps.gsuapp.data.remote.responses.following.FollowingResponses
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
interface GithubClient {
    @GET("search/users")
    suspend fun getSearchUser(@Query("q") query: String): Response<SearchUserResponse>

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") userName: String): Response<DetailResponse>

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(@Path("username") userName: String): Response<FollowersResponses>

    @GET("users/{username}/following")
    suspend fun getUserFollowing(@Path("username") userName: String): Response<FollowingResponses>

}