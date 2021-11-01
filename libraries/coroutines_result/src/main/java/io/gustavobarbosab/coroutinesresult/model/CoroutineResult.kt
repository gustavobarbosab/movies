package io.gustavobarbosab.coroutinesresult.model

sealed class CoroutineResult<out R> {
    data class Success<out T>(val data: T) : CoroutineResult<T>()
    data class Error(val exception: Exception) : CoroutineResult<Nothing>()
}
