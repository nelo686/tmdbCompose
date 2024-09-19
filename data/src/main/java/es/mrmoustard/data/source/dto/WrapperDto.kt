package es.mrmoustard.data.source.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import es.mrmoustard.domain.model.TopRatedWrapper

@JsonClass(generateAdapter = true)
data class WrapperDto(
    val page: Int,
    val results: List<MovieDto>,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "total_results") val totalResults: Int,
)

fun WrapperDto.mapToDomain(): TopRatedWrapper =
    TopRatedWrapper(
        page = page,
        results = results.mapToDomain(),
        totalPages = totalPages,
        totalResults = totalResults
    )
