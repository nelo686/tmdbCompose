package es.mrmoustard.data.source.remote.dto

import es.mrmoustard.domain.model.Genre

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
