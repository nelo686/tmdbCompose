package es.mrmoustard.data.source.local.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.mrmoustard.domain.model.Movie

@Entity
data class MovieStatus(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String = "",
    val backdropPath: String = "",
    val favourite: Boolean = false,
    val wannaWatchIt: Boolean = false
)

fun MovieStatus.mapToDomain(): Movie =
    Movie(
        id = id,
        title = title,
        backdropPath = backdropPath,
        favourite = favourite,
        wannaWatchIt = wannaWatchIt
    )

fun List<MovieStatus>.mapToDomain(): List<Movie> =
    this.map { it.mapToDomain() }
