package es.mrmoustard.data.source.local

import es.mrmoustard.data.source.local.database.dto.MovieStatus

interface MoviesLocalDataSource {

    suspend fun findById(id: Int): MovieStatus?
    suspend fun insert(item: MovieStatus)
    suspend fun delete(item: MovieStatus)
    suspend fun update(movieId: Int, favourite: Boolean = false, wannaWatchIt: Boolean = false)
    suspend fun findFavourites(): List<MovieStatus>
    suspend fun findMoviesToWatch(): List<MovieStatus>
}
