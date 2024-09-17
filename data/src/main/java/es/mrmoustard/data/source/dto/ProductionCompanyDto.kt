package es.mrmoustard.data.source.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import es.mrmoustard.domain.model.ProductionCompany

@JsonClass(generateAdapter = true)
data class ProductionCompanyDto(
    val id: Int,
    val name: String,
    @Json(name = "logo_path") val logoPath: String?,
    @Json(name = "origin_country") val originCountry: String
)

fun ProductionCompanyDto.mapToDomain(): ProductionCompany =
    ProductionCompany(
        id = id,
        name = name,
        logoPath = logoPath ?: "",
        originCountry = originCountry
    )

fun List<ProductionCompanyDto>.mapToDomain(): List<ProductionCompany> =
    this.map { it.mapToDomain() }
