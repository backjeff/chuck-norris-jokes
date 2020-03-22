package io.github.backjeff.chucknorrisjokes.data_remote.util

enum class ErrorMessageEnum(val value: String) {
    GENERIC_ERROR("Connection error")
}

open class DataSourceException(
    message: String = ErrorMessageEnum.GENERIC_ERROR.value,
    val code: String
) : Exception(message, Throwable(message = code))

class ServerError(
    message: String = ErrorMessageEnum.GENERIC_ERROR.value
) : Exception(message)
