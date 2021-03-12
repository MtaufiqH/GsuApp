package taufiq.apps.gsuapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import taufiq.apps.gsuapp.data.remote.responses.search.SearchUserResponse
import javax.inject.Singleton

/**
 * Created By Taufiq on 3/12/2021.
 *
 */

private const val BASE_URL =  "https://api.github.com/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGithubApi() : SearchUserResponse =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchUserResponse::class.java)
        }