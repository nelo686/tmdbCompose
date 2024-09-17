package es.mrmoustard.data.repository

import arrow.core.Either
import es.mrmoustard.data.source.dto.Result
import es.mrmoustard.data.source.dto.mapToDomain
import es.mrmoustard.data.source.remote.MoviesRemoteDataSource
import es.mrmoustard.domain.model.MovieDetail
import es.mrmoustard.domain.model.TopRatedWrapper
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remote: MoviesRemoteDataSource) {
    suspend fun getTopRated(page: Int, region: String = "ES"): Result<TopRatedWrapper> =
        remote.getTopRated(page = page, region = region).fold(
            { Either.Left(it) },
            { Either.Right(it.mapToDomain()) }
        )

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetail> =
        remote.getMovieDetails(movieId = movieId).fold(
            { Either.Left(it) },
            { Either.Right(it.mapToDomain()) }
        )
}
