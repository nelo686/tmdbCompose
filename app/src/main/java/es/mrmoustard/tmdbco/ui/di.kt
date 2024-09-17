package es.mrmoustard.tmdbco.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.mrmoustard.tmdbco.BuildConfig
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @ApiEndpoint
    fun apiEndpointProvider(): String = BuildConfig.BASE_URL

    @Provides
    @Bearer
    fun bearerProvider(): String = BuildConfig.BEARER
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Bearer
