package es.mrmoustard.data.source.local

import es.mrmoustard.data.source.local.database.MoviesDatabase
import es.mrmoustard.data.source.local.database.dto.MovieStatus
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(
    private val db: MoviesDatabase
): MoviesLocalDataSource {

    override suspend fun findById(id: Int): MovieStatus? =
        db.movieDao().findById(id = id)

    override suspend fun insert(item: MovieStatus) {
        db.movieDao().insert(item = item)
    }

    override suspend fun delete(item: MovieStatus) {
        db.movieDao().delete(item = item)
    }

    override suspend fun update(movieId: Int, favourite: Boolean, wannaWatchIt: Boolean) {
        db.movieDao().update(movieId, favourite, wannaWatchIt)
    }

    override suspend fun findFavourites(): List<MovieStatus> =
        db.movieDao().findFavourites()

    override suspend fun findMoviesToWatch(): List<MovieStatus> =
        db.movieDao().findMoviesToWatch()
}
