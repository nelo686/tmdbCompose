package es.mrmoustard.data.repository

import arrow.core.left
import arrow.core.right
import es.mrmoustard.data.source.local.MoviesLocalDataSource
import es.mrmoustard.data.source.local.database.dto.MovieStatus
import es.mrmoustard.data.source.remote.dto.Result
import es.mrmoustard.data.source.remote.dto.mapToDomain
import es.mrmoustard.data.source.remote.dto.toError
import es.mrmoustard.data.source.remote.MoviesRemoteDataSource
import es.mrmoustard.data.source.remote.dto.setStatus
import es.mrmoustard.domain.model.MovieDetail
import es.mrmoustard.domain.model.TopRatedWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val local: MoviesLocalDataSource,
    private val remote: MoviesRemoteDataSource
) {
    private suspend fun findById(id: Int): MovieStatus =
        local.findById(id = id) ?: MovieStatus(id = id)

    private suspend fun insert(item: MovieStatus) {
        local.insert(item)
    }

    private suspend fun delete(item: MovieStatus) {
        local.delete(item)
    }

    private suspend fun update(item: MovieStatus) {
        local.update(
            movieId = item.id,
            favourite = item.favourite,
            wannaWatchIt = item.wannaWatchIt
        )
    }

    suspend fun upgradeOrInsertMovieStatus(item: MovieStatus) {
        val localItem = local.findById(id = item.id)
        when {
            localItem == null -> insert(item = item)
            !item.favourite && !item.wannaWatchIt -> delete(item = item)
            else -> update(item = item)
        }
    }

    suspend fun findFavourites(): Result<List<MovieStatus>> =
        try { local.findFavourites().right() }
        catch (e: Exception) { e.toError().left() }

    suspend fun findMoviesToWatch(): Result<List<MovieStatus>> =
        try { local.findMoviesToWatch().right() }
        catch (e: Exception) { e.toError().left() }

    suspend fun getTopRated(page: Int, region: String = "ES"): Result<TopRatedWrapper> =
        try { remote.getTopRated(page, region).mapToDomain().right() }
        catch (e: Exception) { e.toError().left() }

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetail> =
        try {
            remote
                .getMovieDetails(movieId = movieId)
                .setStatus(findById(id = movieId))
                .mapToDomain()
                .right()
        } catch (e: Exception) { e.toError().left() }
}
