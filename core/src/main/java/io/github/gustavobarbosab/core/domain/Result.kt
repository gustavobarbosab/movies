package io.github.gustavobarbosab.core.domain

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun <R> Result<R>.isSuccess() = this is Result.Success

fun <R> Result<R>?.isError() = this is Result.Error

inline fun <R> Result<R>.onSuccess(success: (R) -> Unit): Result<R>? {
    if (!isSuccess())
        return null

    success((this as Result.Success).data)
    return this
}

inline infix fun <R> Result<R>?.orError(error: (Result.Error) -> Unit) {
    if (isError()) error(this as Result.Error)
}
