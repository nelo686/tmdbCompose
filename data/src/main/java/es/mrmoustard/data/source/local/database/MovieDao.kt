package es.mrmoustard.data.source.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.mrmoustard.data.source.local.database.dto.MovieStatus

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieStatus WHERE id = :id")
    suspend fun findById(id: Int): MovieStatus?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MovieStatus)

    @Delete
    suspend fun delete(item: MovieStatus)

    @Query("UPDATE MovieStatus SET favourite =:favourite, wannaWatchIt =:wannaWatchIt WHERE id =:movieId")
    suspend fun update(movieId: Int, favourite: Boolean = false, wannaWatchIt: Boolean = false)

    @Query("SELECT * FROM MovieStatus WHERE wannaWatchIt = 1")
    suspend fun findMoviesToWatch(): List<MovieStatus>

    @Query("SELECT * FROM MovieStatus WHERE favourite = 1")
    suspend fun findFavourites(): List<MovieStatus>
}