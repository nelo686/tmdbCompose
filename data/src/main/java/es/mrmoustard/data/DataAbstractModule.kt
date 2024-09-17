package es.mrmoustard.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.mrmoustard.data.source.remote.MoviesRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class DataAbstractModule {

    @Binds
    abstract fun moviesRemoteDataSourceProvider(
        dataSource: MoviesRemoteDataSource
    ): MoviesRemoteDataSource
}