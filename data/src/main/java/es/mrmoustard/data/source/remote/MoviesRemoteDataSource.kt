package es.mrmoustard.data.source.remote

import es.mrmoustard.data.source.dto.MovieDetailDto
import es.mrmoustard.data.source.dto.Result
import es.mrmoustard.data.source.dto.WrapperDto

interface MoviesRemoteDataSource {
    suspend fun getTopRated(page: Int, region: String): Result<WrapperDto>
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailDto>
}