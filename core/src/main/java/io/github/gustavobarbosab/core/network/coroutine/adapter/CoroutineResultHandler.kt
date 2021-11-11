package io.github.gustavobarbosab.core.network.coroutine.adapter

import io.gustavobarbosab.coroutinesresult.CoroutineResult
import io.gustavobarbosab.coroutinesresult.ExternalErrorData

interface CoroutineResultHandler {

    fun <T : Any> handleResult(
        result: CoroutineResult<T>,
        onSuccess: (T?) -> Unit,
        onError: () -> Unit
    ) {
        when (result) {
            is CoroutineResult.Success -> onSuccess(result.data)
            else -> onError()
        }
    }

    fun <T : Any, U> handleResult(
        result: CoroutineResult<T>,
        onSuccess: (T?) -> Unit,
        onExternalError: (ExternalErrorData) -> Unit,
        onError: () -> Unit,
    ) {
        when (result) {
            is CoroutineResult.Success -> onSuccess(result.data)
            is CoroutineResult.ExternalError -> onExternalError(result.error)
            else -> onError()
        }
    }
}