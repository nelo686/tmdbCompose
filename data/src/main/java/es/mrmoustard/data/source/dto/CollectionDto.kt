package es.mrmoustard.data.source.dto

import com.google.gson.annotations.SerializedName
import es.mrmoustard.domain.model.Collection

data class CollectionDto(
    val id: Int,
    val name: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String
)

fun CollectionDto.mapToDomain(): Collection =
    Collection(
        id = id,
        name = name,
        posterPath = posterPath ?: "",
        backdropPath = backdropPath
    )
