package es.mrmoustard.data.source.dto

import com.google.gson.annotations.SerializedName
import es.mrmoustard.domain.model.ProductionCompany

data class ProductionCompanyDto(
    val id: Int,
    val name: String,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("origin_country") val originCountry: String
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
