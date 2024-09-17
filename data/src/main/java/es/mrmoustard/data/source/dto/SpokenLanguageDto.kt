package es.mrmoustard.data.source.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import es.mrmoustard.domain.model.SpokenLanguage

@JsonClass(generateAdapter = true)
data class SpokenLanguageDto(
    @Json(name = "iso_639_1") val iso6391: String,
    val name: String
)

fun SpokenLanguageDto.mapToDomain(): SpokenLanguage =
    SpokenLanguage(iso6391 = iso6391, name = name)

fun List<SpokenLanguageDto>.mapToDomain(): List<SpokenLanguage> =
    this.map { it.mapToDomain() }
