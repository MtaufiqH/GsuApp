package taufiq.apps.gsuapp.data.remote.client

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse

/**
 * Created By Taufiq on 3/12/2021.
 *
 */
interface GithubClient {
    @GET("search/users")
    suspend fun getSearchUser(@Query("q") query: String) : Response<SearchUserResponse>



}