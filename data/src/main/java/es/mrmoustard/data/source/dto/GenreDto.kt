package es.mrmoustard.data.source.dto

import com.squareup.moshi.JsonClass
import es.mrmoustard.domain.model.Genre

@JsonClass(generateAdapter = true)
data class GenreDto(
    val id: Int,
    val name: String
)

fun GenreDto.mapToDomain(): Genre = Genre(
    id = id,
    name = name
)

fun List<GenreDto>.mapToDomain(): List<Genre> =
    this.map { it.mapToDomain() }
