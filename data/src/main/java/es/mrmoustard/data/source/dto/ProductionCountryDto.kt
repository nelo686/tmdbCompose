package es.mrmoustard.data.source.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import es.mrmoustard.domain.model.ProductionCountry

@JsonClass(generateAdapter = true)
data class ProductionCountryDto(
    @Json(name = "iso_3166_1") val iso31661: String,
    val name: String
)

fun ProductionCountryDto.mapToDomain(): ProductionCountry =
    ProductionCountry(iso31661 = iso31661, name = name)

fun List<ProductionCountryDto>.mapToDomain(): List<ProductionCountry> =
    this.map { it.mapToDomain() }
