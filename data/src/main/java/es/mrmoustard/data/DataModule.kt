package es.mrmoustard.data

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.mrmoustard.data.source.remote.MoviesRemoteDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @ApiEndpoint
    fun apiEndpointProvider(): String = BuildConfig.BASE_URL

    @Provides
    @Bearer
    fun bearerProvider(): String = BuildConfig.BEARER

    @Provides
    fun loggingInterceptorProvider(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun okHttpClientProvider(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()

                    val request = chain.request().newBuilder()
                        .header("Content-Type", "application/json;charset=utf-8")
                        .header("Authorization", "Bearer ${bearerProvider()}")
                        .method(original.method, original.body)
                        .build()

                    return chain.proceed(request)
                }
            })
            .build()

    @Provides
    fun restAdapterProvider(
        @ApiEndpoint apiEndPoint: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiEndPoint)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Provides
    fun moviesRemoteDataSourceProvider(restAdapter: Retrofit): MoviesRemoteDataSource =
        restAdapter.create()
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndpoint

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Bearer