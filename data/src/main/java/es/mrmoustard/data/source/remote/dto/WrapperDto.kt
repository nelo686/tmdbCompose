package es.mrmoustard.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import es.mrmoustard.domain.model.TopRatedWrapper

data class WrapperDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)

fun WrapperDto.mapToDomain(): TopRatedWrapper =
    TopRatedWrapper(
        page = page,
        results = results.mapToDomain(),
        totalPages = totalPages,
        totalResults = totalResults
    )
