package io.github.gustavobarbosab.core.domain

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

fun <R> Result<R>.isSuccess() = this is Result.Success
fun <R> Result<R>.isError() = !isSuccess()
fun <R> Result<R>.data() =
    if (isSuccess()) (this as Result.Success).data
    else null

