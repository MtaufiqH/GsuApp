package taufiq.apps.gsuapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import taufiq.apps.gsuapp.BuildConfig
import taufiq.apps.gsuapp.data.local.FavoriteDatabase
import taufiq.apps.gsuapp.data.local.FavoriteUserDao
import taufiq.apps.gsuapp.data.remote.client.GithubClient
import taufiq.apps.gsuapp.utils.Const
import taufiq.apps.gsuapp.utils.Const.API_KEY
import taufiq.apps.gsuapp.utils.Const.BASE_URL
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created By Taufiq on 3/12/2021.
 *
 */


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val origin = chain.request()
                val requestBuilder = origin.newBuilder()
                    .header("Authorization", API_KEY)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubApi(okHttpClient: OkHttpClient): GithubClient =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubClient::class.java)


    @Singleton
    @Provides
    fun provideFavoriteDb(@ApplicationContext appContext: Context): FavoriteDatabase =
        Room.databaseBuilder(appContext, FavoriteDatabase::class.java, Const.DB_NAME).build()

    @Singleton
    @Provides
    fun providesFavDao(favoriteDatabase: FavoriteDatabase): FavoriteUserDao =
        favoriteDatabase.getUserFavoriteDao()
}