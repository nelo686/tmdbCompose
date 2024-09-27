package es.mrmoustard.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.mrmoustard.data.source.local.database.dto.MovieStatus
import kotlin.concurrent.Volatile

@Database(entities = [MovieStatus::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var instance: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = MoviesDatabase::class.java,
                    name = "movies.db"
                )
                    .build()
                    .also { instance = it }
            }
        }
    }
}