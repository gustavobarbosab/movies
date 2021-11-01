package io.gustavobarbosab.coroutinesresult

import io.gustavobarbosab.coroutinesresult.model.CoroutineResult

interface CoroutineResultHandler {

    fun <T> handleResult(
        result: CoroutineResult<T>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (result) {
            is CoroutineResult.Success -> onSuccess(result.data)
            is CoroutineResult.Error -> onError(result.exception)
        }
    }
}