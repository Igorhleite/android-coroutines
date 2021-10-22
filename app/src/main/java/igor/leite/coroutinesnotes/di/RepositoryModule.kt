package igor.leite.coroutinesnotes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import igor.leite.coroutinesnotes.data.ApiClient
import igor.leite.coroutinesnotes.data.repository.GetCardRepository
import igor.leite.coroutinesnotes.data.repository.GetCardRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRemoteMoviesRepository(
        apiClient: ApiClient,
    ): GetCardRepository {
        return GetCardRepositoryImpl(apiClient)
    }
}