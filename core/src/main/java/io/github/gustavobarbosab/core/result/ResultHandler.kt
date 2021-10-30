package io.github.gustavobarbosab.core.result

import io.github.gustavobarbosab.core.domain.Result

interface ResultHandler {

    fun <T> handleResult(
        result: Result<T>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit
    ) {
        when (result) {
            is Result.Success -> onSuccess(result.data)
            is Result.Error -> onError(result.exception)
        }
    }
}