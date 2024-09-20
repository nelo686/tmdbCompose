package es.mrmoustard.data.repository

import arrow.core.Either
import es.mrmoustard.data.source.dto.Error
import es.mrmoustard.data.source.dto.Result
import es.mrmoustard.data.source.dto.WrapperDto
import es.mrmoustard.data.source.dto.mapToDomain
import es.mrmoustard.data.source.remote.MoviesRemoteDataSource
import es.mrmoustard.domain.model.MovieDetail
import es.mrmoustard.domain.model.TopRatedWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val remote: MoviesRemoteDataSource) {
    suspend fun getTopRated(page: Int, region: String = "ES"): Result<TopRatedWrapper> {
        val result = remote.getTopRated(page = page, region = region)
        return if (result is WrapperDto) {
            Either.Right(result.mapToDomain())
        } else {
            Either.Left(Error.Unknown(message = "Unknown Error"))
        }
    }

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetail> =
        remote.getMovieDetails(movieId = movieId).fold(
            { Either.Left(it) },
            { Either.Right(it.mapToDomain()) }
        )
}
