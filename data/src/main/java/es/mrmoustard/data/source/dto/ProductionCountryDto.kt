package es.mrmoustard.data.source.dto

import com.google.gson.annotations.SerializedName
import es.mrmoustard.domain.model.ProductionCountry

data class ProductionCountryDto(
    @SerializedName("iso_3166_1") val iso31661: String,
    val name: String
)

fun ProductionCountryDto.mapToDomain(): ProductionCountry =
    ProductionCountry(iso31661 = iso31661, name = name)

fun List<ProductionCountryDto>.mapToDomain(): List<ProductionCountry> =
    this.map { it.mapToDomain() }
