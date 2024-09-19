package es.mrmoustard.data.source.remote

import es.mrmoustard.data.source.dto.MovieDetailDto
import es.mrmoustard.data.source.dto.Result
import es.mrmoustard.data.source.dto.WrapperDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteDataSource {

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int,
        @Query("region") region: String
    ): Result<WrapperDto>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): Result<MovieDetailDto>
}