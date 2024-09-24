package es.mrmoustard.data.repository

import arrow.core.Either
import es.mrmoustard.data.source.dto.Result
import es.mrmoustard.data.source.dto.mapToDomain
import es.mrmoustard.data.source.dto.toError
import es.mrmoustard.data.source.remote.MoviesRemoteDataSource
import es.mrmoustard.domain.model.MovieDetail
import es.mrmoustard.domain.model.TopRatedWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val remote: MoviesRemoteDataSource) {
    suspend fun getTopRated(page: Int, region: String = "ES"): Result<TopRatedWrapper> =
        try { Either.Right(remote.getTopRated(page, region).mapToDomain()) }
        catch (e: Exception) { Either.Left(e.toError()) }

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetail> =
        try { Either.Right(remote.getMovieDetails(movieId = movieId).mapToDomain()) }
        catch (e: Exception) { Either.Left(e.toError()) }
}
