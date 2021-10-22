package igor.leite.coroutinesnotes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import igor.leite.coroutinesnotes.BuildConfig
import igor.leite.coroutinesnotes.data.ApiClient
import igor.leite.coroutinesnotes.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideLevel(
    ): HttpLoggingInterceptor.Level {
        return HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideHttpLogging(
        level: HttpLoggingInterceptor.Level,
    ): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = level
        return logging
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .readTimeout(4000, TimeUnit.MILLISECONDS)
            .connectTimeout(4000, TimeUnit.MILLISECONDS)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(logging)
        }
        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
    }

    @Singleton
    @Provides
    fun provideMovieApi(
        retrofit: Retrofit.Builder,
    ): ApiClient {
        return retrofit
            .build()
            .create(ApiClient::class.java)
    }
}