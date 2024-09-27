package es.mrmoustard.data.source.remote.api

import es.mrmoustard.data.source.remote.dto.MovieDetailDto
import es.mrmoustard.data.source.remote.dto.WrapperDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int,
        @Query("region") region: String
    ): WrapperDto

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): MovieDetailDto
}