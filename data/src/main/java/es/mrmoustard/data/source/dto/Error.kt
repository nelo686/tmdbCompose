package es.mrmoustard.data.source.dto

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.HttpException
import java.io.IOException

typealias Result<T> = Either<Error, T>

sealed class Error {
    object Connectivity: Error()
    class Server(val code: Int): Error()
    class Unknown(val message: String): Error()
}

fun Exception.toError(): Error = when(this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message = message ?: "")
}

inline fun <T> tryCall(action: () -> T): Result<T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}
