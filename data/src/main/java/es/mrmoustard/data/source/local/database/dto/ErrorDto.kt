package es.mrmoustard.data.source.local.database.dto

import es.mrmoustard.data.source.remote.dto.Error

data class ErrorDto(val message: String)

fun ErrorDto.mapToDomain() = Error.Unknown(message)