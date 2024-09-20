package es.mrmoustard.data.source.dto

import com.google.gson.annotations.SerializedName
import es.mrmoustard.domain.model.SpokenLanguage

data class SpokenLanguageDto(
    @SerializedName("iso_639_1") val iso6391: String,
    val name: String
)

fun SpokenLanguageDto.mapToDomain(): SpokenLanguage =
    SpokenLanguage(iso6391 = iso6391, name = name)

fun List<SpokenLanguageDto>.mapToDomain(): List<SpokenLanguage> =
    this.map { it.mapToDomain() }
